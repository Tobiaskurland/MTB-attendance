package com.example.demo.Repository.View;

import com.example.demo.Model.Views.AttendanceOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAttendanceOverviewRepo extends JpaRepository<AttendanceOverview, Integer> {

    @Query(value = "select * from attendance_overview a where a.userID = ?1 and a.courseID = ?2 and a.attendance = 'not attended' and a.lecture_status = 'done'", nativeQuery = true)
    List<AttendanceOverview> findByAbsence(int userID, int courseID);

    @Query(value = "select * from attendance_overview a where a.userID = ?1 and a.courseID = ?2 and a.attendance = 'attended'", nativeQuery = true)
    List<AttendanceOverview> findByAttended(int userID, int courseID);
}
