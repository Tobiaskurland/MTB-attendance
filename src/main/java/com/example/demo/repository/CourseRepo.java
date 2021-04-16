package com.example.demo.repository;

import com.example.demo.Model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer>
{
    public List<CourseEntity> findByClassID(int classID);
}
