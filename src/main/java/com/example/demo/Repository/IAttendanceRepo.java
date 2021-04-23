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
}
