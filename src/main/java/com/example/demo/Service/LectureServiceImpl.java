package com.example.demo.Service;

import com.example.demo.Model.Lecture;
import com.example.demo.Repository.ICourseRepo;
import com.example.demo.Repository.ILectureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class LectureServiceImpl implements ILectureService{

    @Autowired
    ILectureRepo lectureRepo;

    @Autowired
    ICourseRepo courseRepo;

    @Override
    public List<Lecture> findAll() {
        return lectureRepo.findAll();
    }

    @Override
    public List<Lecture> findByCourseId(int course_id, int class_id) {
        return lectureRepo.findLectureByCourse_id(course_id, class_id);
    }

    @Override
    public List<Lecture> findByCourseAndDate(int course_id, int user_id) {
        return lectureRepo.findLectureByCourseAndDate(course_id, user_id);
    }

    @Override
    public Lecture findById(int id) {
        return lectureRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        lectureRepo.deleteById(id);
    }

    @Override
    public void save(Lecture lecture) {
        lectureRepo.save(lecture);
    }

    @Override
    public void saveAll(List<Lecture> lecture) {
        lectureRepo.saveAll(lecture);
    }

    @Override
    public Lecture matchingCodes(int id, String enteredCode) {
        return lectureRepo.matchingCodes(id, enteredCode);
    }

    @Override
    public void saveLecture(Lecture lecture, int class_id, int startLecture, int endLecture, int course_id) {

        int loopCount = endLecture - startLecture;
        int lectureModule = startLecture;
        int course_class_id = courseRepo.findCourseClassByClassAndCourse(class_id, course_id);

        for (int i = 0; i < loopCount + 1; i++){
            Lecture l = new Lecture();
            l.setLectureName(lecture.getLectureName());
            l.setDate(lecture.getDate());
            l.setTimeInterval(lectureModule);
            l.setCourse_class_id(course_class_id);

            lectureRepo.save(l);
            lectureModule++;
        }
    }

    @Override
    public List<Lecture> findLecturesByCourseIdForDate(int id, int user_id, LocalDate date)
    {
        return lectureRepo.findLecturesByCourseIdForDate(id, user_id, date);
    }

    @Override
    public List<Lecture> findLecturesByDate(LocalDate date)
    {
        return lectureRepo.findLecturesByDate(date);
    }

    @Override
    public List<Lecture> findLecturesByCourseIdForWeek(int id, int user_id, int weekNumber, int year)
    {
        LocalDate startDate = LocalDate.now()
                .withYear(year)
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        LocalDate endDate = startDate.plusDays(7);

        return lectureRepo.findLecturesByCourseIdForDaterange(id, user_id, startDate, endDate);
    }

    @Override
    public List<Lecture> findAllPassedLectures(int classId) {
        return lectureRepo.findAllPassedLectures(classId);
    }
}
