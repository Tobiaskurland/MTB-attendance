package com.example.demo.Service;

import com.example.demo.Model.Attendance;
import com.example.demo.Repository.IAttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

    @Autowired
    IAttendanceRepo attendanceRepo;

    @Override
    public List<Attendance> findAll() {
        return attendanceRepo.findAll();
    }

    @Override
    public List<Attendance> findAllByUserId(int id) {
        return attendanceRepo.getAttendanceByUser_id(id);
    }

    @Override
    public Attendance findById(int id) {
        return attendanceRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        attendanceRepo.deleteById(id);
    }

    @Override
    public void save(Attendance attendanceEntity) {
        attendanceRepo.save(attendanceEntity);
    }

    @Override
    public Attendance alreadyAttended(int userId, int lectureId) {
        return attendanceRepo.alreadyAttended(userId, lectureId);
    }
}
