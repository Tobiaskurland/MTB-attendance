package com.example.demo.Service;

import com.example.demo.Model.UserClass;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public interface IUserClassService {

    void save(UserClass userClass);
}
