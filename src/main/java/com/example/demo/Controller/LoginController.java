package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.LoginService;
import com.example.demo.Service.SignupService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class LoginController {

    //Logger
    Logger log = Logger.getLogger(LoginController.class.getName());

    @Autowired
    LoginService loginService;
    @Autowired
    SignupService signupService;

    //Is the user a teacher?
    private boolean teacher;


    //LOGIN
    @CrossOrigin()
    @GetMapping("/")
    public String login(Model model){

        return "login";
    }
    @CrossOrigin()
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {

        //See if the user consist in the database
        User user = loginService.findLogin(email, password);

        //If the user doesn't exist, redirect to login page
        if (user == null){

            String error = "Username or password was incorrect!";

            System.out.println(error);

            model.addAttribute("error", "logError");

            return "redirect:/";

        } else{ //Else set the user to this.user

            session.setAttribute("login", user);

            return "redirect:/overview";
        }
    }

    // LOGOUT
    @CrossOrigin()
    @GetMapping("/logout")
    public String logout(HttpSession session){

        log.info("logout called... ");

        //Remove the session from the client
        session.removeAttribute("login");
        //Reset the teacher boolean
        this.teacher = false;

        log.info("session terminated");

        return "/login";
    }

    //TEMP HOME
    @CrossOrigin()
    @GetMapping("/home")
    public String TempHome(HttpSession session, Model model){

        //If a user is loggedIn
        if (session.getAttribute("login") != null){

            //Get the user from the session
            User u = (User)session.getAttribute("login");

            //Data we need on the HTML
            model.addAttribute("user", u);
            model.addAttribute("teacher", this.teacher);

            return "home";
        }else {

            //model.addAttribute("notLoggedIn", "notLoggedIn");
            return "error";
        }
    }
}
