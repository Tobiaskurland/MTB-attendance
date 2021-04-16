package com.example.demo.service;

import com.example.demo.Model.CourseEntity;
import com.example.demo.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseServiceImpl implements CourseService
{
    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<CourseEntity> getCoursesByClassID(int courseID)
    {
        return courseRepo.findByClassID(courseID);
    }

    @Override
    public CourseEntity getCourse(int courseID)
    {
        return courseRepo.findById(courseID).get();
    }
}
