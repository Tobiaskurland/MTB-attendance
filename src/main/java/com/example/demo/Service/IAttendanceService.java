package com.example.demo.Service;

import com.example.demo.Model.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAttendanceService {

    List<Attendance> findAll();
    List<Attendance> findAllByUserId(int id);
    Attendance findById(int id);
    void deleteById(int id);
    void save(Attendance attendanceEntity);
}
