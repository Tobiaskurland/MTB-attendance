package com.example.demo.Controller;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Lecture;
import com.example.demo.Model.User;
import com.example.demo.Service.AttendanceServiceImpl;
import com.example.demo.Service.ILectureService;
import com.example.demo.Utility.GenerateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
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

    //Initial page - TEACHER
    @CrossOrigin
    @GetMapping("/lecture/{lectureid}/attendance/createcode")
    public String TeacherAttendance(HttpSession session, Model model, @PathVariable int lectureid){

        //If a user is loggedIn
        if (session.getAttribute("login") != null){

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //Get the current lecture
            Lecture l = lectureService.findById(lectureid);
            String timer = null;
            boolean hasCodeExpired = false;

            if(l.getCodeExpire() != null){

                Date d = l.getCodeExpire();
                Date now = new Date();

                DateFormat targetFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss", Locale.US);
                timer = targetFormat.format(d);

                //Check if the time has expired so you cant generate a code anymore - HAS TO RELOAD SITE THOUGH
                if (d.before(now)){
                    hasCodeExpired = true;
                }
            }

            //Data we need on the HTML
            model.addAttribute("user", u);
            model.addAttribute("role", u.getRole_id());
            model.addAttribute("l", l);
            model.addAttribute("date", l.getDate());
            model.addAttribute("time_interval", l.getTimeInterval());
            model.addAttribute("lectureid", lectureid);
            model.addAttribute("generatedCode", l.getVerificationCode());
            model.addAttribute("hasCodeExpired", hasCodeExpired);
            model.addAttribute("timer", timer);

            log.info("TIMER: " + timer);

            return "CodeAttendance";
        }else {

            //model.addAttribute("notLoggedIn", "notLoggedIn");
            return "error";
        }
    }

    //Initial page - STUDENT
    @CrossOrigin
    @GetMapping("/lecture/{lectureid}/attendance")
    public String StudentAttendance(HttpSession session, Model model, @PathVariable int lectureid) throws ParseException {

        //If a user is loggedIn
        if (session.getAttribute("login") != null){

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //Get the current lecture
            Lecture l = lectureService.findById(lectureid);
            String timer = null;
            boolean hasCodeExpired = false;

            if(l.getCodeExpire() != null){

                Date d = l.getCodeExpire();
                Date now = new Date();

                DateFormat targetFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss", new Locale("en"));
                timer = targetFormat.format(d);

                //Check if the time has expired so you cant enter code anymore - HAS TO RELOAD SITE THOUGH
                if (d.before(now)){
                    hasCodeExpired = true;
                }
            }

            //Data we need on the HTML
            model.addAttribute("user", u);
            model.addAttribute("role", u.getRole_id());
            model.addAttribute("l", l);
            model.addAttribute("date", l.getDate());
            model.addAttribute("time_interval", l.getTimeInterval());
            model.addAttribute("lectureid", lectureid);
            model.addAttribute("hasCodeExpired", hasCodeExpired);
            model.addAttribute("timer", timer);

            log.info("TIMER: " + timer);

            return "CodeAttendance";
        }else {

            //model.addAttribute("notLoggedIn", "notLoggedIn");
            return "error";
        }
    }

    //Generate code function - TEACHER
    @CrossOrigin
    @GetMapping("/GenerateCode/{id}")
    public String GenerateCode(HttpSession session, Model model, @PathVariable int id){

        if (session.getAttribute("login") != null) {

            //Set the CODE_EXPIRE to 5 minutes after NOW()
            Timestamp timestamp = new Timestamp(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));

            //Get the random generated alphanumeric string
            String code = utility.randomAlphanumericString();

            //A good/bad way to UPDATE in JPA?
            Lecture l = lectureService.findById(id);
            l.setVerificationCode(code);
            l.setCodeExpire(timestamp);
            lectureService.save(l);

            log.info("Generated Code: " + code);

            //"Reload" the page with the new generated code
            return "redirect:/lecture/" + l.getLectureId() + "/attendance/createcode";
        }

        return "error";
    }

    //Enter code function - STUDENT
    @CrossOrigin
    @PostMapping("/StudentCode/{id}")
    public String StudentCode(HttpSession session, Model model, @PathVariable int id, @ModelAttribute("enteredCode") String enteredCode){

        if (session.getAttribute("login") != null) {

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //Check if the code matches the one in the database
            Lecture l = lectureService.matchingCodes(id, enteredCode);

            //Check if the student has already attended
            Attendance attend = attendanceService.alreadyAttended(u.getUserId(), id);

            //If the codes matches give the Student attendance to that lecture
            if (l != null && attend == null) {
                Attendance a = new Attendance();

                a.setUser_id(u.getUserId());
                a.setLecture_id(id);

                attendanceService.save(a);

                //Go to the success-page
                return "redirect:/success/" + id;

            //Else redirect to incorrect-page
            }else {

                return "redirect:/incorrect/" + id;
            }
        }

        return "error";
    }

    @CrossOrigin
    @GetMapping("/success/{id}")
    public String Success(HttpSession session, @PathVariable int id, Model model){

        if (session.getAttribute("login") != null){

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //Get the current lecture
            Lecture l = lectureService.findById(id);

            //Data we need on the HTML
            model.addAttribute("user", u);
            model.addAttribute("l", l.getLectureName());

            return "success";
        }

        return "error";
    }

    @CrossOrigin
    @GetMapping("/incorrect/{id}")
    public String Incorrect(HttpSession session, @PathVariable int id, Model model, HttpServletRequest request){

        if (session.getAttribute("login") != null){

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //Get the current lecture
            Lecture l = lectureService.findById(id);

            //Data we need on the HTML
            model.addAttribute("user", u);
            model.addAttribute("l", l.getLectureName());
            model.addAttribute("lectureid", id);

            return "incorrect";

        }

        return "error";
    }
}
