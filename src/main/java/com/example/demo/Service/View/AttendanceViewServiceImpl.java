package com.example.demo.Service.View;

import com.example.demo.Model.Views.AttendanceView;
import com.example.demo.Repository.View.IAttendanceViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceViewServiceImpl implements IAttendanceViewService{

    @Autowired
    IAttendanceViewRepo attendanceViewRepo;

    @Override
    public List<AttendanceView> findAll() {
        return attendanceViewRepo.findAll();
    }

    @Override
    public List<AttendanceView> findAllByUserId(int id) {
        return attendanceViewRepo.findAttendanceViewByUserId(id);
    }

    @Override
    public AttendanceView findById(int id) {
        return attendanceViewRepo.getOne(id);
    }
}
