package com.example.demo.service;

import com.example.demo.Model.LectureEntity;

import java.util.List;

public interface LectureService
{
    public List<LectureEntity> getLecturesByCourseId(int courseId);

}
