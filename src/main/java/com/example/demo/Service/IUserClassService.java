package com.example.demo.Service;

import com.example.demo.Model.UserClass;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface IUserClassService {

    void save(UserClass userClass);
    List<UserClass> findAllStudentInClass(int classId);
    int findClassIdForStudent(int userId);

}
