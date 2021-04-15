package com.example.demo.Repository;

import com.example.demo.Model.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo {
    UserEntity findLogin(String firstName, String password);
}
