package com.example.demo.Service;

import com.example.demo.Model.CourseClass;
import org.springframework.stereotype.Service;

@Service
public interface ICourseClassService {

    void save(CourseClass courseClass);
}
