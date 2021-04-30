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
    List<Clazz> findClassByUser(int user_id);
}
