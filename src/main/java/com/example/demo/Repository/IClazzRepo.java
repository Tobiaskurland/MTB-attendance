package com.example.demo.Repository;

import com.example.demo.Model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClazzRepo extends JpaRepository<Clazz, Integer> {

    @Query(value = "select cl.* from user_class uc " +
            "join class cl on cl.classID = uc.class_id " +
            "where uc.user_id = ?1", nativeQuery = true)
    List<Clazz> findClassesByUser(int user_id);

    @Query(value = "select cl.* from user_class uc " +
            "join class cl on cl.classID = uc.class_id " +
            "where uc.user_id = ?1", nativeQuery = true)
    Clazz findclassByUserId(int user_id);

    @Query(value = "select cl.* from user_class uc " +
            "join class cl on cl.classID = uc.class_id " +
            "join course_class cc on cc.class_id = uc.class_id " +
            "where uc.user_id = ?1 and cc.course_id = ?2", nativeQuery = true)
    List<Clazz> findClassesByUserAndCourse(int user_id, int course_id);


}
