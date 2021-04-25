package com.example.demo.Repository;

import com.example.demo.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ICourseRepo extends JpaRepository<Course, Integer> {

    @Query(value = "select c.* from course c " +
            "JOIN course_class cc on cc.course_id = c.courseID where cc.class_id = ?1", nativeQuery = true)
    List<Course> findCourseByClassId(int classId);

    @Query(value = "select c.* from user_class uc " +
            "join class cl on cl.classID = uc.class_id " +
            "join course_class cc on cc.class_id = cl.classID " +
            "join course c on c.courseID = cc.course_id " +
            "where uc.user_id = ?1 ", nativeQuery = true)
    List<Course> findCourseByUserId(int userId);


}
