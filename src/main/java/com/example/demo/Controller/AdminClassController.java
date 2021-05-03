package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminClassController {

    @GetMapping("/admin/class")
    public String AdminClass() {

        return "";
    }
}
