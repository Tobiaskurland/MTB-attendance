package com.example.demo.Service;

import com.example.demo.Model.Education;
import com.example.demo.Repository.IEducationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements IEducationService{

    @Autowired
    IEducationRepo educationRepo;

    @Override
    public List<Education> findAll() {
        return educationRepo.findAll();
    }

    @Override
    public void save(Education education) {

        educationRepo.save(education);
    }
}
