package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Model.Views.AttendanceView;
import com.example.demo.Service.AttendanceServiceImpl;
import com.example.demo.Service.CourseServiceImpl;
import com.example.demo.Service.LectureServiceImpl;
import com.example.demo.Service.UserServiceImpl;
import com.example.demo.Service.View.AttendanceViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/student/statistics/{userId}")
    public String getStudentStatistics(@PathVariable int userId, Model model){

        User theUser = userService.findById(userId);

        model.addAttribute("user", theUser);
        model.addAttribute("course", courseService.findCourseByClassId(theUser.getClassId()));

        return "statistics";
    }

    @GetMapping("/student/statistics/{userId}/result")
    public String getStudentStatisticsResult(@RequestParam int course, @PathVariable int userId, Model model){

        User theUser = userService.findById(userId);
        List<AttendanceView> theView = attendanceViewService.findAllByUserId(userId);

        model.addAttribute("user", theUser);
        model.addAttribute("course", courseService.findCourseByClassId(theUser.getClassId()));
        model.addAttribute("attendanceOverview", attendanceViewService.findAllByUserId(userId));

        return "statistics";
    }
}