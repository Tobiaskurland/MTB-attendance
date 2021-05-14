package com.example.demo.Controller;


import com.example.demo.Model.User;
import com.example.demo.Service.IAttendanceService;
import com.example.demo.Service.IClazzService;
import com.example.demo.Service.ILectureService;
import com.example.demo.Service.IUserClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;


@Controller
public class ClassStatisticsController {


    Logger log = Logger.getLogger(ClassStatisticsController.class.getName());

    @Autowired
    IClazzService clazzService;

    @Autowired
    ILectureService lectureService;

    @Autowired
    IUserClassService userClassService;

    @Autowired
    IAttendanceService attendanceService;

    @GetMapping("/class/statistics")
    public String ClassStatistics(HttpSession session, Model model){

        if(getLoggedInUser(session) != null)
        {
            User u = (User)session.getAttribute("login");

            model.addAttribute("user", u);
            model.addAttribute("class", clazzService.findAll());
            model.addAttribute("attendance", 0.0);
            model.addAttribute("studentsInClass", 0);
            model.addAttribute("lectures", 0);
            return "classstatistics";
        }

        return "error";
    }

    @GetMapping("/class/statistics/result")
    public String getClassStatisticsResult(@RequestParam(name = "class") int classId, Model model, HttpSession session){

        if(getLoggedInUser(session) != null)
        {
            int passedLectures = lectureService.findAllPassedLectures(classId).size();
            int studentsInClass = userClassService.findAllStudentInClass(classId).size();
            int attendanceInClass = attendanceService.findAllAttendanceOnClass(classId).size();
            double attendancePercent = 0.0;


            if (passedLectures > 0 || studentsInClass > 0){
                attendancePercent = attendanceService.calculateAttendanceOnClass(passedLectures, studentsInClass, attendanceInClass);
            }

            User u = (User)session.getAttribute("login");

            model.addAttribute("user", u);
            model.addAttribute("class", clazzService.findAll());
            model.addAttribute("attendance", attendancePercent);
            model.addAttribute("studentsInClass", studentsInClass);
            model.addAttribute("lectures", passedLectures);

            return "classstatistics";

        }
        return "error";
    }

    //Get the logged in User
    private User getLoggedInUser(HttpSession session)
    {
        try
        {
            User user = (User) session.getAttribute("login");
            return user;
        }
        catch (Exception e)
        {
            return null;
        }
    }

}
