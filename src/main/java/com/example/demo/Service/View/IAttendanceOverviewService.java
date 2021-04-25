package com.example.demo.Service.View;

import com.example.demo.Model.Views.AttendanceOverview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAttendanceOverviewService{

    List<AttendanceOverview> findByAbsence(int userId, int courseId);
    List<AttendanceOverview> findByAttended(int userId, int courseId);
}
