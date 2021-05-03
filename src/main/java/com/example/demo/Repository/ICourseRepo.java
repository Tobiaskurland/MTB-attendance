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

    @Query(value = "select c.* from user_course uc " +
            "join course_class cc on uc.course_class_id = cc.idcourse_class " +
            "join course c on cc.course_id = c.courseID " +
            "join class cl on cl.classID = cc.class_id " +
            "join user_class ucl on ucl.user_id = uc.user_id and ucl.class_id = cl.classID " +
            "where uc.user_id = ?2 and cl.classID = ?1", nativeQuery = true)
    List<Course> findByClassAndUser(int class_id, int user_id);

    @Query(value = "select idcourse_class from course_class where class_id = ?1 and course_id = ?2", nativeQuery = true)
    int findCourseClassByClassAndCourse(int class_id, int course_id);

}
