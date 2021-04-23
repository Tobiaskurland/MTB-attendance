package com.example.demo.Repository.View;

import com.example.demo.Model.Views.AttendanceView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAttendanceViewRepo extends JpaRepository<AttendanceView, Integer> {

    @Query(value = "select * from attendance_view a where a.userID = ?1 and a.courseID = ?2", nativeQuery = true)
    List<AttendanceView> findAttendanceViewByUserAndCourse(int userId, int courseId);

   @Query(value = "select * from attendance_view a where a.userID = ?1 and a.courseID = ?2 and a.lecture_status = 'not attended'",  nativeQuery = true)
    List<AttendanceView> findByAbsence(int userID, int courseID);
}
