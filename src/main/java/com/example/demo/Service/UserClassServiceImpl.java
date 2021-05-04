package com.example.demo.Service;

import com.example.demo.Model.UserClass;
import com.example.demo.Repository.IUserClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserClassServiceImpl implements IUserClassService{

    @Autowired
    IUserClassRepo userClassRepo;

    @Override
    public void save(UserClass userClass) {
        userClassRepo.save(userClass);
    }
}
