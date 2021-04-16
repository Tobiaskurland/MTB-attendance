package com.example.demo.controller;

import com.example.demo.Model.ClazzEntity;
import com.example.demo.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ClazzController
{
    @Autowired
    private ClazzService clazzService;

    @GetMapping("/classes/{userId}")
    public List<ClazzEntity> getClassesForUser(@PathVariable int userId)
    {
        return clazzService.getClassesByUserId(userId);
    }
}
