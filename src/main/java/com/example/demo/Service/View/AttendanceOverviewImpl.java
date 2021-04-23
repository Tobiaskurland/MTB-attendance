package com.example.demo.Service.View;

import com.example.demo.Model.Views.AttendanceOverview;
import com.example.demo.Repository.View.IAttendanceOverviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceOverviewImpl implements IAttendanceOverviewService{

    @Autowired
    IAttendanceOverviewRepo attendanceOverviewRepo;

    @Override
    public List<AttendanceOverview> findByAbsence(int userId, int courseId) {
        return attendanceOverviewRepo.findByAbsence(userId, courseId);
    }

    @Override
    public List<AttendanceOverview> findByAttended(int userId, int courseId) {
        return attendanceOverviewRepo.findByAttended(userId, courseId);
    }
}
