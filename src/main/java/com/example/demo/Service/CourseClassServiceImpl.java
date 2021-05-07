package com.example.demo.Service;

import com.example.demo.Model.CourseClass;
import com.example.demo.Repository.ICourseClassRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseClassServiceImpl implements ICourseClassService{

    @Autowired
    ICourseClassRepo courseClassRepo;

    @Override
    public void save(CourseClass courseClass) {

        courseClassRepo.save(courseClass);
    }
}
