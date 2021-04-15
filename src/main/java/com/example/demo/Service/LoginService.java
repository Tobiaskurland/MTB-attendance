package com.example.demo.Service;

import com.example.demo.Model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    UserEntity findLogin(String firstName, String password);
}
