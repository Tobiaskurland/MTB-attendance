package com.example.demo.Service.View;

import com.example.demo.Model.Views.AttendanceView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAttendanceViewService {

    List<AttendanceView> findAll();
    List<AttendanceView> findAllByUserId(int id);
    AttendanceView findById(int id);
}
