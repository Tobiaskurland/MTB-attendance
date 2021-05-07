package com.example.demo.Repository;

import com.example.demo.Model.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseClassRepo extends JpaRepository<CourseClass, Integer> {

}
