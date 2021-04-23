package com.example.demo.Repository.View;

import com.example.demo.Model.Views.AttendanceView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAttendanceViewRepo extends JpaRepository<AttendanceView, Integer> {

    @Query("select a from AttendanceView a where a.userID = ?1")
    List<AttendanceView> findAttendanceViewByUserId(int id);
}
