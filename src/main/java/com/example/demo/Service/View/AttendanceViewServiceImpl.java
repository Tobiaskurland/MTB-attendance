package com.example.demo.Service.View;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Clazz;
import com.example.demo.Model.Lecture;
import com.example.demo.Model.Views.AttendanceOverview;
import com.example.demo.Model.Views.AttendanceView;
import com.example.demo.Repository.IClazzRepo;
import com.example.demo.Repository.ILectureRepo;
import com.example.demo.Repository.View.IAttendanceOverviewRepo;
import com.example.demo.Repository.View.IAttendanceViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceViewServiceImpl implements IAttendanceViewService{

    @Autowired
    IAttendanceViewRepo attendanceViewRepo;

    @Autowired
    ILectureRepo lectureRepo;

    @Autowired
    IAttendanceOverviewRepo attendanceOverviewRepo;

    @Autowired
    IClazzRepo clazzRepo;

    @Override
    public List<AttendanceView> findAll() {
        return attendanceViewRepo.findAll();
    }

    @Override
    public List<AttendanceView> findAllByUserAndCourse(int userId, int courseId) {
        return attendanceViewRepo.findAttendanceViewByUserAndCourse(userId, courseId);
    }

    @Override
    public AttendanceView findById(int id) {
        return attendanceViewRepo.getOne(id);
    }

    public double calculatePercent(List<Attendance> attendanceList, List<Lecture> lectureList){

        int totalLectures = lectureList.size();
        int attendedLectures = attendanceList.size();

        double attendancePercent = (attendedLectures * 100) / totalLectures;

        return attendancePercent;
    }

    public double calculateAbsensePercent(int userId, int courseId){

        int class_id = 0;

        if (userId > 0){
            Clazz theClass = clazzRepo.findclassByUserId(userId);
            class_id = theClass.getClassId();
        }

        List<Lecture> lectureByCourse = lectureRepo.findLectureByCourse_id(courseId, class_id);
        List<AttendanceOverview> attendanceViewList = attendanceOverviewRepo.findByAbsence(userId, courseId);

        double absencePercent = 0.0;

        if (lectureByCourse.size() != 0){
            if (attendanceViewList.size() != 0){
                absencePercent = (attendanceViewList.size() * 100) / lectureByCourse.size();
            }else {
                absencePercent = 0.0;
            }
        }

        return absencePercent;
    }
}
