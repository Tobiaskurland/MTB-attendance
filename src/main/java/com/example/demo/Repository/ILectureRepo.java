package com.example.demo.Repository;

import com.example.demo.Model.Course;
import com.example.demo.Model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ILectureRepo extends JpaRepository<Lecture, Integer> {

    @Query("select l from Lecture l where l.course_id = ?1")
    List<Lecture> findLectureByCourse_id(int id);

    @Query(value = "select * from lecture l where l.course_id = ?1 and l.date < CURDATE()", nativeQuery = true)
    List<Lecture> findLectureByCourseAndDate(int course_id);

    @Query(value = "select * from lecture where course_id = ?1 and date between ?2 and ?3", nativeQuery = true)
    List<Lecture> findLecturesByCourseIdForDaterange(int id, LocalDate startDate, LocalDate endDate);

    @Query(value = "select * from lecture where course_id = ?1 and date = ?2 order by date, time_interval", nativeQuery = true)
    List<Lecture> findLecturesByCourseIdForDate(int id, LocalDate date);

    @Query(value = "select * from lecture where lectureID = ?1 and verification_code = ?2 and code_expire > NOW()", nativeQuery = true)
    Lecture matchingCodes(int id, String enteredCode);
}
