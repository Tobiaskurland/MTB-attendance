package com.example.demo.repository;

import com.example.demo.Model.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepo extends JpaRepository<LectureEntity, Integer>
{
    public List<LectureEntity> findByCourseId(int courseId);
}
