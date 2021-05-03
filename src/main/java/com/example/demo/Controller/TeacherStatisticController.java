package com.example.demo.Controller;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Lecture;
import com.example.demo.Model.User;
import com.example.demo.Service.*;
import com.example.demo.Service.View.AttendanceOverviewImpl;
import com.example.demo.Service.View.AttendanceViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TeacherStatisticController {

    @Autowired
    IUserService userService;

    @Autowired
    ICourseService courseService;

    @Autowired
    IClazzService clazzService;

    @Autowired
    AttendanceViewServiceImpl attendanceViewService;

    @Autowired
    AttendanceOverviewImpl attendanceOverview;

    @Autowired
    LectureServiceImpl lectureService;

    @Autowired
    AttendanceServiceImpl attendanceService;


    @GetMapping("/teacher/statistics")
    public String getStudentStatistics(Model model, HttpSession session, @RequestParam(value = "classId", defaultValue = "0") int classId,
                                       @RequestParam(value = "courseId", defaultValue = "0") int courseId,
                                       @RequestParam(value = "studentId", defaultValue = "0") int studentId){

        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        User u = (User)session.getAttribute("login");
        if (u.getRole_id() == 1){
            return "redirect:/overview";
        }

        int userId = u.getUserId();
        User theUser = userService.findById(userId);

        double absencePercent = attendanceViewService.calculateAbsensePercent(studentId, courseId);
        double attendancePercent = 0.0;

        List<Lecture> lectuerByCourseAndDate = lectureService.findByCourseAndDate(courseId, userId);
        List<Attendance> attendanceList = attendanceService.findAllByUserId(studentId);

        if (attendanceList.size() > 0 && lectuerByCourseAndDate.size() > 0){
            attendancePercent = attendanceViewService.calculatePercent(attendanceList, lectuerByCourseAndDate);

        }

        model.addAttribute("students", userService.findUsersByClassAndCourse(classId, courseId));
        model.addAttribute("studemt", studentId);
        model.addAttribute("user", theUser);
        model.addAttribute("percent", attendancePercent);
        model.addAttribute("classes", clazzService.findClassesByUser(userId));
        model.addAttribute("class", classId);
        model.addAttribute("courses", courseService.findByClassAndUser(classId, userId));
        model.addAttribute("course", courseId);
        model.addAttribute("attendedLectures", attendanceOverview.findByAttended(studentId, courseId).size());
        model.addAttribute("attendanceOverview", attendanceViewService.findAllByUserAndCourse(studentId, courseId));
        model.addAttribute("absencePercent", absencePercent);

        return "statistics";
    }


}
