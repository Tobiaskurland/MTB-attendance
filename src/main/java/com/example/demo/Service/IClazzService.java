package com.example.demo.Service;

import com.example.demo.Model.Clazz;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface IClazzService {

    List<Clazz> findAll();
    List<Clazz> findByUser(int id);
    Clazz findById(int id);
    void deleteById(int id);
    void save(Clazz clazz);
}
