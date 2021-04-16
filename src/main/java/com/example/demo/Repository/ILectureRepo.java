package com.example.demo.Repository;

import com.example.demo.Model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILectureRepo extends JpaRepository<Lecture, Integer> {

    @Query("select l from Lecture l where l.course_id = ?1")
    List<Lecture> findLectureByCourse_id(int id);
}
