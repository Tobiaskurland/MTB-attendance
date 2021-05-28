package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginRepo loginRepo;

    @Override
    public User findLogin(String email, String password) {
        return loginRepo.findLogin(email, password);
    }
}
