package com.example.demo.Service;

import com.example.demo.Model.Clazz;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface IClazzService {

    List<Clazz> findAll();
    List<Clazz> findClassesByUser(int id);
    List<Clazz> findClassByUserAndCourse(int user_id, int course_id);
    Clazz findClassByUserId(int id);
    Clazz findById(int id);
    void deleteById(int id);
    Clazz save(Clazz clazz);
    void deleteClass(int id);
}
