package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.IClazzService;
import com.example.demo.Service.IEducationService;
import com.example.demo.Service.IUserClassService;
import com.example.demo.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    IUserService userService;

    @Autowired
    IUserClassService userClassService;

//READ CLASS
    @GetMapping("/admin/class")
    public String AdminClass(HttpSession session, Model model) {

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        //If a user is loggedIn
        if (session.getAttribute("login") != null && u.getRole_id() == 3) {

            if(u.getRole_id() > 2) {

                //All classes
                model.addAttribute("user", u);
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
                model.addAttribute("user", u);
                model.addAttribute("classes", clazzService.findAll());
                model.addAttribute("role", u.getRole_id());
                model.addAttribute("educations", educationService.findAll());
            }
            return "addClass";
        }
        return "error";
    }

    @PostMapping("/admin/addClass")
    public String addClass(@ModelAttribute Clazz clazz, HttpSession session){

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        if(session.getAttribute("login") != null && u.getRole_id() == 3) {
            clazzService.save(clazz);

            return "redirect:/admin/addClass/success";
        }
        return "error";
    }

    @GetMapping("/admin/addClass/success")
    public String addClassSuccess(HttpSession session, Model model) {

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        if(session.getAttribute("login") != null && u.getRole_id() == 3)
        {
            model.addAttribute("user", u);
            return "addclasssuccess";
        }
        return "error";
    }

//UPDATE CLASS
    @GetMapping("/admin/{classId}/updateClass")
    public String updateClass(@PathVariable int classId, HttpSession session, Model model){

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        if(session.getAttribute("login") != null && u.getRole_id() == 3)
        {
            model.addAttribute("user", u);
            model.addAttribute("classId", classId);
            model.addAttribute("students", userService.findAllStudentsWithNoClass());
            model.addAttribute("studentswithclass", userService.findALlStudentsWithClass(classId));
            model.addAttribute("role", u.getRole_id());
            model.addAttribute("educations", educationService.findAll());

            return "updateClass";
        }
        return "error";
    }

    @PostMapping("/admin/{classId}/updateClass")
    public String UpdateClass(@PathVariable int classId, HttpSession session, @ModelAttribute ClazzViewModel updatedClazz){

        //Get the user from the session
        User u = (User)session.getAttribute("login");

        if(session.getAttribute("login") != null && u.getRole_id() == 3) {

            Clazz oldClazz = clazzService.findById(classId);
            updatedClazz.getClazz().setClassId(classId);

            //If the Class Name input is empty keep the old class name
            if(updatedClazz.getClazz().getClassName() == ""){
                updatedClazz.getClazz().setClassName(oldClazz.getClassName());
            }

            //If the Education input has not been picked, keep the old education
            if(updatedClazz.getClazz().getEducation_id() == 0){
                updatedClazz.getClazz().setEducation_id(oldClazz.getEducation_id());
            }

            clazzService.save(updatedClazz.getClazz());

            //Attach the users to the class
            if(updatedClazz.getUserIdList() != null) {
                for (Integer user_id : updatedClazz.getUserIdList()) {
                    UserClass uc = new UserClass();

                    uc.setUser_id(user_id);
                    uc.setClass_id(classId);

                    userClassService.save(uc);
                }
            }
            return "redirect:/admin/addClass/success";
        }
        return "error";
    }

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
