package com.example.demo.Service;

import com.example.demo.Model.UserEntity;
import com.example.demo.Repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginRepo loginRepo;

    @Override
    public UserEntity findLogin(String firstName, String password) {
        return loginRepo.findLogin(firstName, password);
    }
}
