package com.example.demo.service;

import com.example.demo.Model.ClazzEntity;
import com.example.demo.repository.ClazzRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService
{
    @Autowired
    private ClazzRepo clazzRepo;

    public List<ClazzEntity> getClassesByUserId(int id)
    {
        return clazzRepo.findByEducationId(id);
    }
}
