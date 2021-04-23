package com.example.demo.Controller;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Lecture;
import com.example.demo.Model.User;
import com.example.demo.Model.Views.AttendanceView;
import com.example.demo.Service.AttendanceServiceImpl;
import com.example.demo.Service.CourseServiceImpl;
import com.example.demo.Service.LectureServiceImpl;
import com.example.demo.Service.UserServiceImpl;
import com.example.demo.Service.View.AttendanceOverviewImpl;
import com.example.demo.Service.View.AttendanceViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class StudentStatisticController {

    @Autowired
    AttendanceServiceImpl attendanceService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    LectureServiceImpl lectureService;

    @Autowired
    AttendanceViewServiceImpl attendanceViewService;

    @Autowired
    AttendanceOverviewImpl attendanceOverview;

    @GetMapping("/student/statistics/{userId}")
    public String getStudentStatistics(@PathVariable int userId, Model model, HttpSession session){

        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        User u = (User)session.getAttribute("login");
        User theUser = userService.findById(userId);
        if (theUser.getFirstName().equals(u.getFirstName()) && theUser.getLastName().equals(u.getLastName())) {

            model.addAttribute("user", theUser);
            model.addAttribute("percent", 0.0);
            model.addAttribute("absencePercent", 0.0);
            model.addAttribute("courses", courseService.findCourseByClassId(theUser.getClassId()));
            model.addAttribute("attendedLectures", 0);

            return "statistics";
        }
        return "error";
    }

    @GetMapping("/student/statistics/{userId}/result")
    public String getStudentStatisticsResult(@RequestParam int course, @PathVariable int userId, Model model, HttpSession session){

        if (session.getAttribute("login") == null){
            return "redirect:/";
        }

        User u = (User)session.getAttribute("login");
        User theUser = userService.findById(userId);
        if (theUser.getFirstName().equals(u.getFirstName()) && theUser.getLastName().equals(u.getLastName())){

            List<AttendanceView> attendanceViewList = attendanceViewService.findAllByUserAndCourse(userId, course);
            LocalDate now = LocalDate.now();
            List<Lecture> lectuerByCourseAndDate = lectureService.findByCourseAndDate(course);
            List<Attendance> attendanceList = attendanceService.findAllByUserId(userId);
            double attendancePercent = 0.0;

            double absencePercent = attendanceViewService.calculateAbsensePercent(userId, course);

            if (attendanceList.size() > 0 && lectuerByCourseAndDate.size() > 0){
                attendancePercent = attendanceViewService.calculatePercent(attendanceList, lectuerByCourseAndDate);

            }

            model.addAttribute("user", theUser);
            model.addAttribute("course", course);
            model.addAttribute("courses", courseService.findCourseByClassId(theUser.getClassId()));
            model.addAttribute("attendanceOverview", attendanceViewList);
            model.addAttribute("localDate", now);
            model.addAttribute("percent", attendancePercent);
            model.addAttribute("absencePercent", absencePercent);
            model.addAttribute("attendedLectures", attendanceOverview.findByAttended(userId, course).size());

            return "statistics";
        }

        return "error";

    }
}