package com.example.demo.Service;

import com.example.demo.Model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface IUserService {

    List<User> findAll();
    List<User> findUsersByClassAndCourse(int class_id, int course_id);
    User findById(int id);
    void deleteById(int id);
    void save(User user);
    List<User> findAllStudentsWithNoClass();
    List<User> findALlStudentsWithClass(int classId);
}
