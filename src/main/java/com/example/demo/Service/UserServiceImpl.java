package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findUsersByClassAndCourse(int class_id, int course_id) {
        return userRepo.findUsersByClassAndCourse(class_id, course_id);
    }

    @Override
    public User findById(int id) {
        return userRepo.findUserById(id);
    }

    @Override
    public void deleteById(int id) {
        userRepo.deleteById(id);

    }

    @Override
    public void save(User user) {
        userRepo.save(user);

    }

    @Override
    public List<User> findAllStudentsWithNoClass() {
        return userRepo.findAllStudentsWithNoClass();
    }

    @Override
    public List<User> findALlStudentsWithClass(int classId) {
        return userRepo.findALlStudentsWithClass(classId);
    }
}
