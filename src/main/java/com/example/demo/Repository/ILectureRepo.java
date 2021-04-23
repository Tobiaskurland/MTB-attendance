package com.example.demo.Repository;

import com.example.demo.Model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ILectureRepo extends JpaRepository<Lecture, Integer> {

    @Query("select l from Lecture l where l.course_id = ?1")
    List<Lecture> findLectureByCourse_id(int id);

    @Query(value = "select * from Lecture l where l.course_id = ?1 and l.date < CURDATE()", nativeQuery = true)
    List<Lecture> findLectureByCourseAndDate(int course_id);
}
