package com.example.demo.Controller;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Lecture;
import com.example.demo.Model.User;
import com.example.demo.Model.Views.AttendanceView;
import com.example.demo.Service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class ClassStatisticsController {


    Logger log = Logger.getLogger(ClassStatisticsController.class.getName());

    @Autowired
    IClazzService clazzService;

    @GetMapping("/class/statistics")
    public String ClassStatistics(HttpSession session, Model model){

        if(getLoggedInUser(session) != null)
        {
            User u = (User)session.getAttribute("login");

            model.addAttribute("user", u);
            model.addAttribute("class", clazzService.findAll());
            return "classstatistics";
        }

        return "error";
    }

    @GetMapping("/class/statistics/result")
    public String getClassStatisticsResult(@RequestParam int classId, Model model, HttpSession session){

        if(getLoggedInUser(session) != null)
        {

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
