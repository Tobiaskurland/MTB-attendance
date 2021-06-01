package com.example.demo.Controller;

import com.example.demo.Model.Lecture;
import com.example.demo.Model.User;
import com.example.demo.Repository.ICourseRepo;
import com.example.demo.Service.IClazzService;
import com.example.demo.Service.ICourseService;
import com.example.demo.Service.ILectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LectureController {

    @Autowired
    IClazzService clazzService;

    @Autowired
    ICourseService courseService;

    @Autowired
    ILectureService lectureService;

    @GetMapping("/course/{courseId}/addLecture")
    public String addLecture(Model model, HttpSession session, @PathVariable int courseId){

        if (session.getAttribute("login") == null){
            return "redirect:/";
        }

        User u = (User)session.getAttribute("login");
        if (u.getRole_id() == 1){
            return "redirect:/overview";
        }

        model.addAttribute("user", u);
        model.addAttribute("classes", clazzService.findClassByUserAndCourse(u.getUserId(), courseId));
        model.addAttribute("course", courseService.findById(courseId));

        return "addLecture";
    }

    @PostMapping("/course/{courseId}/addLecture")
    public String addLecture(@ModelAttribute Lecture lecture, @RequestParam int class_id, @RequestParam int startLecture, @RequestParam int endLecture, @PathVariable int courseId){

        lectureService.saveLecture(lecture, class_id, startLecture, endLecture, courseId);

        return "redirect:/overview";
    }
}
