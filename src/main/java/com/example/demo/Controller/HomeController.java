package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/header")
    public String getHeader(Model model, HttpSession session){

        User u = (User)session.getAttribute("login");

        model.addAttribute("user", userService.findById(u.getUserId()));

        return "statics/header";
    }
}
