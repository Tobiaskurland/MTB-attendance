package com.example.demo.Service;

import com.example.demo.Model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface SignupService {
    void signup(UserEntity u);
}
