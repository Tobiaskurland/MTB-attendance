package com.example.demo.service;

import com.example.demo.Model.CourseEntity;
import com.example.demo.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService
{
    public List<CourseEntity> getCoursesByClassID(int courseID);
    public CourseEntity getCourse(int courseID);
}
