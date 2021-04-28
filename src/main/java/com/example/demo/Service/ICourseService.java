package com.example.demo.Service;

import com.example.demo.Model.Course;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface ICourseService {
    List<Course> findAll();
    List<Course> findCourseByClassId(int id);
    List<Course> findCourseByUserId(int id);
    List<Course> findByClassAndUser(int class_id, int user_id);
    Course findById(int id);
    void deleteById(int id);
    void save(Course course);
}
