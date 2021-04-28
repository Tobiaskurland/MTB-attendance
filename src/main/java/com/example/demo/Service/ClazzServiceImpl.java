package com.example.demo.Service;

import com.example.demo.Model.Clazz;
import com.example.demo.Repository.IClazzRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements IClazzService{

    @Autowired
    IClazzRepo clazzRepo;

    @Override
    public List<Clazz> findAll() {
        return clazzRepo.findAll();
    }

    @Override
    public List<Clazz> findByUser(int id) {
        return clazzRepo.findClassByUser(id);
    }

    @Override
    public Clazz findById(int id) {
        return clazzRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        clazzRepo.deleteById(id);
    }

    @Override
    public void save(Clazz clazz) {
        clazzRepo.save(clazz);
    }
}
