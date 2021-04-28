package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.IClazzService;
import com.example.demo.Service.ICourseService;
import com.example.demo.Service.IUserService;
import com.example.demo.Service.View.AttendanceOverviewImpl;
import com.example.demo.Service.View.AttendanceViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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

        model.addAttribute("students", userService.findUsersByClassAndCourse(classId, courseId));
        model.addAttribute("studemt", studentId);
        model.addAttribute("user", theUser);
        model.addAttribute("percent", 0.0);
        model.addAttribute("classes", clazzService.findByUser(userId));
        model.addAttribute("class", classId);
        model.addAttribute("courses", courseService.findByClassAndUser(classId, userId));
        model.addAttribute("course", courseId);
        model.addAttribute("attendedLectures", attendanceOverview.findByAttended(studentId, courseId).size());
        model.addAttribute("attendanceOverview", attendanceViewService.findAllByUserAndCourse(studentId, courseId));
        model.addAttribute("absencePercent", absencePercent);

        return "statistics";
    }


}
