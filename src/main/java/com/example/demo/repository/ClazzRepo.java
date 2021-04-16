package com.example.demo.repository;

import com.example.demo.Model.ClazzEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ClazzRepo extends JpaRepository<ClazzEntity, Integer>
{
    public List<ClazzEntity> findByEducationId(int id);
}
