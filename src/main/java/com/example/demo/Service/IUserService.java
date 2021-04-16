package com.example.demo.Service;

import com.example.demo.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> findAll();
    User findById(int id);
    void deleteById(int id);
    void save(User user);
}
