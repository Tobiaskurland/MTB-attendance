package com.example.demo.Repository;

import com.example.demo.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ICourseRepo extends JpaRepository<Course, Integer> {

    @Query("select c from Course c where c.class_id = ?1")
    List<Course> findCourseByClassId(int classId);


}
