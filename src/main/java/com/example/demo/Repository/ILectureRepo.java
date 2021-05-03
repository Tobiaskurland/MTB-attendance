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

    @Query( value = "select * from course_class cc " +
            "join lecture l on cc.idcourse_class = l.course_class_id " +
            "where cc.course_id = ?1 and cc.class_id = ?2", nativeQuery = true)
    List<Lecture> findLectureByCourse_id(int course_id, int class_id);

    @Query(value = "select * from course_class cc " +
            "JOIN lecture l on cc.idcourse_class = l.course_class_id " +
            "JOIN user_class uc on uc.class_id = cc.class_id " +
            "where cc.course_id = ?1 and uc.user_id = ?2 and l.date < CURDATE()", nativeQuery = true)
    List<Lecture> findLectureByCourseAndDate(int course_id, int user_id);

    @Query(value = "select * from course_class cc " +
            "JOIN lecture l on cc.idcourse_class = l.course_class_id " +
            "JOIN user_class uc on uc.class_id = cc.class_id " +
            "where cc.course_id = ?1 and uc.user_id = ?2 and date between ?3 and ?4", nativeQuery = true)
    List<Lecture> findLecturesByCourseIdForDaterange(int id, int user_id, LocalDate startDate, LocalDate endDate);

    @Query(value = "select * from course_class cc " +
            "JOIN lecture l on cc.idcourse_class = l.course_class_id " +
            "JOIN user_class uc on uc.class_id = cc.class_id " +
            "where cc.course_id = ?1 and uc.user_id = ?2 and date = ?3 order by date, time_interval", nativeQuery = true)
    List<Lecture> findLecturesByCourseIdForDate(int id, int user_id, LocalDate date);

    @Query(value = "select * from lecture where date = ?1", nativeQuery = true)
    List<Lecture> findLecturesByDate(LocalDate date);

    @Query(value = "select * from lecture where lectureID = ?1 and verification_code = ?2 and code_expire > NOW()", nativeQuery = true)
    Lecture matchingCodes(int id, String enteredCode);

}
