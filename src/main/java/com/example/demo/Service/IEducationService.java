package com.example.demo.Service;

import com.example.demo.Model.Education;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface IEducationService {

    List<Education> findAll();
    void save(Education education);
}
