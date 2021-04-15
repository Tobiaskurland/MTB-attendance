package com.example.demo.Controller;

import com.example.demo.Model.UserEntity;
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

    Logger log = Logger.getLogger(LoginController.class.getName());

    @Autowired
    LoginService loginService;
    @Autowired
    SignupService signupService;

    private UserEntity user;


    //LOGIN
    @CrossOrigin()
    @GetMapping("/")
    public String login(Model model){

        return "login";
    }
    @CrossOrigin()
    @PostMapping("/login")
    public String login(@RequestParam String firstName, @RequestParam String password, HttpSession session, Model model) {

        UserEntity user = loginService.findLogin(firstName, password);

        if (user == null){

            String error = "Username or password was incorrect!";

            System.out.println(error);

            model.addAttribute("error", "logError");

            return "redirect:/";

        } else{
            this.user = user;
            session.setAttribute("login", this.user);
            return "redirect:/home";
        }
    }

    // LOGOUT
    @CrossOrigin()
    @GetMapping("/logout")
    public String logout(HttpSession session){

        log.info("logout called... ");

        session.removeAttribute("login");

        log.info("session terminated");

        return "/login";
    }

    // SIGN UP
    @CrossOrigin()
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @CrossOrigin()
    @PostMapping("/signup")
    public String signup(@ModelAttribute UserEntity user, HttpSession session, Model model) throws JSONException {

        signupService.signup(user);

        return "redirect:/";
    }

    //TEMP HOME
    @CrossOrigin()
    @GetMapping("/home")
    public String TempHome(HttpSession session, Model model){

        if (session.getAttribute("login") != null){

            UserEntity u = (UserEntity)session.getAttribute("login");

            model.addAttribute("user", u);

            return "home";
        }else {

            //model.addAttribute("notLoggedIn", "notLoggedIn");
            return "error";
        }
    }
}
