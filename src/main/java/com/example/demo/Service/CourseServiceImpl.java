package com.example.demo.Service;

import com.example.demo.Model.Course;
import com.example.demo.Repository.ICourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    ICourseRepo courseRepo;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public List<Course> findCourseByClassId(int id) {
        return courseRepo.findCourseByClassId(id);
    }

    @Override
    public Course findById(int id) {
        return courseRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        courseRepo.deleteById(id);
    }

    @Override
    public void save(Course course) {
        courseRepo.save(course);
    }
}
