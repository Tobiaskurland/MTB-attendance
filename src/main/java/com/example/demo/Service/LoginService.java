package com.example.demo.Service;

import com.example.demo.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    User findLogin(String email, String password);
}
