package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo {
    User findLogin(String firstName, String password);
}
