package com.example.demo.Repository;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Views.AttendanceView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAttendanceRepo extends JpaRepository<Attendance, Integer> {

    @Query("SELECT a from Attendance a where a.user_id = ?1")
    List<Attendance> getAttendanceByUser_id(int id);

    @Query(value = "select * from attendance where user_id = ?1 AND lecture_id = ?2", nativeQuery = true)
    Attendance alreadyAttended(int userId, int lectureId);

    @Query(value = "SELECT * FROM attendance WHERE lecture_id IN " +
            "( " +
                "SELECT lectureID FROM lecture WHERE course_class_id IN " +
                "( " +
                    "SELECT idcourse_class FROM course_class WHERE class_id = (SELECT classID FROM Class WHERE classID = ?1) " +
                ") " +
            ")", nativeQuery = true)
    List<Attendance> findAllAttendanceOnClass(int classId);

}
