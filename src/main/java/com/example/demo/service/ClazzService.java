package com.example.demo.service;

import com.example.demo.Model.ClazzEntity;

import java.util.List;

public interface ClazzService
{
    public List<ClazzEntity> getClassesByUserId(int id);
}
