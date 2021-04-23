package com.example.demo.Service;

import com.example.demo.Model.Lecture;
import com.example.demo.Repository.ILectureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LectureServiceImpl implements ILectureService{

    @Autowired
    ILectureRepo lectureRepo;

    @Override
    public List<Lecture> findAll() {
        return lectureRepo.findAll();
    }

    @Override
    public List<Lecture> findByCourseId(int id) {
        return lectureRepo.findLectureByCourse_id(id);
    }

    @Override
    public List<Lecture> findByCourseAndDate(int id) {
        return lectureRepo.findLectureByCourseAndDate(id);
    }

    @Override
    public Lecture findById(int id) {
        return lectureRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        lectureRepo.deleteById(id);
    }

    @Override
    public void save(Lecture lecture) {
        lectureRepo.save(lecture);
    }
}
