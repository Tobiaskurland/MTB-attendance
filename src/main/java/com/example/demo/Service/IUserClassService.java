package com.example.demo.Service;

import com.example.demo.Model.UserClass;
import org.springframework.stereotype.Service;

@Service
public interface IUserClassService {

    void save(UserClass userClass);
}
