package com.example.demo.Service;

import com.example.demo.Model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICourseService {
    List<Course> findAll();
    List<Course> findCourseByClassId(int id);
    Course findById(int id);
    void deleteById(int id);
    void save(Course course);
}
