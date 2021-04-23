package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.SignupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService{

    @Autowired
    SignupRepo signupRepo;

    @Override
    public void signup(User u) {
        signupRepo.signup(u);
    }
}
