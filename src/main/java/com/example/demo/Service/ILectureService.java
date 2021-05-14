package com.example.demo.Service;

import com.example.demo.Model.Course;
import com.example.demo.Model.Lecture;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Primary
public interface ILectureService {

    List<Lecture> findAll();
    List<Lecture> findByCourseId(int course_id, int class_id);
    List<Lecture> findByCourseAndDate(int course_id, int user_id);
    List<Lecture> findLecturesByDate(LocalDate date);
    Lecture findById(int id);
    void deleteById(int id);
    void save(Lecture lecture);
    void saveAll(List<Lecture> lecture);
    List<Lecture> findLecturesByCourseIdForWeek(int id, int user_id, int weekNumber, int year);
    List<Lecture> findLecturesByCourseIdForDate(int id, int user_id, LocalDate date);
    Lecture matchingCodes(int id, String enteredCode);
    void saveLecture(Lecture lecture, int class_id, int startLecture, int  endLecture, int course_id);
    List<Lecture> findAllPassedLectures(int classId);
}
