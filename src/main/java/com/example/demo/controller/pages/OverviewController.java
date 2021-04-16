package com.example.demo.controller.pages;

import com.example.demo.Model.CourseEntity;
import com.example.demo.Model.LectureEntity;
import com.example.demo.service.CourseService;
import com.example.demo.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OverviewController
{
    @Autowired
    private LectureService lectureService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{courseID}")
    public String getCourseOverviewPage(@PathVariable int courseID, Session session, Model model)
    {
        List<LectureEntity> lectures = lectureService.getLecturesByCourseId(courseID);
        CourseEntity course = courseService.getCourse(courseID);

        model.addAttribute("lectures", lectures);
        model.addAttribute("course", course);

        return "CourseOverview";
    }

    @GetMapping("/overview")
    public String getOverviewPage(Session session, Model model)
    {
        List<CourseEntity> courses = courseService.getCoursesByClassID(1);

        model.addAttribute("courses", courses);

        return "Overview";
    }
}
