package com.example.demo.service;

import com.example.demo.Model.LectureEntity;
import com.example.demo.repository.LectureRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LectureServiceImpl implements LectureService
{
    @Autowired
    private LectureRepo lectureRepo;

    @Override
    public List<LectureEntity> getLecturesByCourseId(int courseId)
    {
        return lectureRepo.findByCourseId(courseId);
    }
}
