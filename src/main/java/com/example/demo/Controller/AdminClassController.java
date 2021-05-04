package com.example.demo.Controller;

import com.example.demo.Model.Clazz;
import com.example.demo.Model.User;
import com.example.demo.Service.IClazzService;
import com.example.demo.Service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class AdminClassController {

    //Logger
    Logger log = Logger.getLogger(LoginController.class.getName());

    @Autowired
    IClazzService clazzService;

    @Autowired
    IEducationService educationService;

//READ CLASS
    @GetMapping("/admin/class")
    public String AdminClass(HttpSession session, Model model) {

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        //If a user is loggedIn
        if (session.getAttribute("login") != null && u.getRole_id() == 3) {

            if(u.getRole_id() > 2) {


                //All classes
                model.addAttribute("classes", clazzService.findAll());
                model.addAttribute("role", u.getRole_id());
            }
            return "admin_class";
        }

        return "error";
    }

//CREATE CLASS
    @GetMapping("/admin/addClass")
    public String AddClass(HttpSession session, Model model){

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        //If a user is loggedIn
        if (session.getAttribute("login") != null && u.getRole_id() == 3) {

            if(u.getRole_id() > 2) {

                //All classes
                model.addAttribute("classes", clazzService.findAll());
                model.addAttribute("role", u.getRole_id());
                model.addAttribute("educations", educationService.findAll());
            }
            return "addClass";
        }
        return "error";
    }

    @PostMapping("/admin/addClass")
    public String addLecture(@ModelAttribute Clazz clazz, HttpSession session){

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        if(session.getAttribute("login") != null && u.getRole_id() == 3) {
            clazzService.save(clazz);

            return "redirect:/admin/addClass/success";
        }
        return "error";
    }

    @GetMapping("/admin/addClass/success")
    public String addClassSuccess(HttpSession session) {

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        if(session.getAttribute("login") != null && u.getRole_id() == 3)
        {
            return "addclasssuccess";
        }
        return "error";
    }

//UPDATE CLASS

//DELETE CLASS
    @GetMapping("/admin/{classId}/deleteClass")
    public String deleteClass(@PathVariable int classId, HttpSession session){

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        if(session.getAttribute("login") != null && u.getRole_id() == 3)
        {
            clazzService.deleteClass(classId);

            log.info("Class deleted with ID: " + classId);
            return "redirect:/admin/class";
        }
        return "error";
    }
}
