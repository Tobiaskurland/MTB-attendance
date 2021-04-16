package com.example.demo.Service;

import com.example.demo.Model.Lecture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILectureService {

    List<Lecture> findAll();
    List<Lecture> findByCourseId(int id);
    Lecture findById(int id);
    void deleteById(int id);
    void save(Lecture lecture);
}
