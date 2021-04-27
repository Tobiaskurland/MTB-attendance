package com.example.demo.Controller;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Lecture;
import com.example.demo.Model.User;
import com.example.demo.Service.AttendanceServiceImpl;
import com.example.demo.Service.IAttendanceService;
import com.example.demo.Service.ILectureService;
import com.example.demo.Utility.GenerateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Controller
public class CodeController {

    //Logger
    Logger log = Logger.getLogger(LoginController.class.getName());

    //Class to generate code
    GenerateCode utility = new GenerateCode();

    @Autowired
    ILectureService lectureService;

    @Autowired
    AttendanceServiceImpl attendanceService;

    @CrossOrigin
    @GetMapping("/lecture/{lectureid}/attendance/createcode")
    public String TeacherAttendance(HttpSession session, Model model, @PathVariable int lectureid){

        //If a user is loggedIn
        if (session.getAttribute("login") != null){

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //Get the current lecture
            Lecture l = lectureService.findById(lectureid);

            //Data we need on the HTML
            model.addAttribute("role", u.getRole_id());
            model.addAttribute("l", l);
            model.addAttribute("date", l.getDate());
            model.addAttribute("time_interval", l.getTimeInterval());
            model.addAttribute("lectureid", lectureid);
            model.addAttribute("generatedCode", l.getVerificationCode());


            return "CodeAttendance";
        }else {

            //model.addAttribute("notLoggedIn", "notLoggedIn");
            return "error";
        }
    }

    @CrossOrigin
    @GetMapping("/lecture/{lectureid}/attendance")
    public String StudentAttendance(HttpSession session, Model model, @PathVariable int lectureid){

        //If a user is loggedIn
        if (session.getAttribute("login") != null){

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //Get the current lecture
            Lecture l = lectureService.findById(lectureid);

            //Data we need on the HTML
            model.addAttribute("role", u.getRole_id());
            model.addAttribute("l", l);
            model.addAttribute("date", l.getDate());
            model.addAttribute("time_interval", l.getTimeInterval());
            model.addAttribute("lectureid", lectureid);

            return "CodeAttendance";
        }else {

            //model.addAttribute("notLoggedIn", "notLoggedIn");
            return "error";
        }
    }

    @CrossOrigin
    @GetMapping("/GenerateCode/{id}")
    public String GenerateCode(HttpSession session, Model model, @PathVariable int id){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));

        String code = utility.randomAlphanumericString();

        Lecture l = lectureService.findById(id);
        l.setVerificationCode(code);
        l.setCodeExpire(timestamp);

        lectureService.save(l);

        log.info("Generated Code: " + code);

        return "redirect:/lecture/" + l.getLectureId() + "/attendance/createcode";
    }

    @CrossOrigin
    @PostMapping("/StudentCode/{id}")
    public String StudentCode(HttpSession session, @PathVariable int id, @ModelAttribute("enteredCode") String enteredCode){

        if (session.getAttribute("login") != null) {

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //See if the code matches the one in the database
            Lecture l = lectureService.matchingCodes(id, enteredCode);

            if (l != null) {
                Attendance a = new Attendance();

                a.setUser_id(u.getUserId());
                a.setLecture_id(id);

                attendanceService.save(a);
            }

            log.info("Code: " + enteredCode);

            return "redirect:/lecture/" + id + "/attendance";
        }

        return "error";
    }
}
