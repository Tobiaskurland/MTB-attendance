package com.example.demo.Model.Views;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "attendance_overview", schema = "MTD_attend", catalog = "")
public class AttendanceOverview {
    private int attendanceViewID;
    private int userId;
    private String firstName;
    private String lastName;
    private int classId;
    private String className;
    private int courseId;
    private String courseName;
    private int lectureId;
    private String lectureName;
    private Date date;
    private String attendance;
    private String lecture_status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "attendance_view_id")
    public int getAttendanceViewID() {
        return attendanceViewID;
    }

    public void setAttendanceViewID(int attendanceViewID) {
        this.attendanceViewID = attendanceViewID;
    }

    @Basic
    @Column(name = "userID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "classID")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "class_name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "courseID")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "lectureID")
    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    @Basic
    @Column(name = "lecture_name")
    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "attendance")
    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @Basic
    @Column(name = "lecture_status")
    public String getLecture_status() {
        return lecture_status;
    }

    public void setLecture_status(String lecture_status) {
        this.lecture_status = lecture_status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceOverview that = (AttendanceOverview) o;
        return userId == that.userId && classId == that.classId && courseId == that.courseId && lectureId == that.lectureId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(className, that.className) && Objects.equals(courseName, that.courseName) && Objects.equals(lectureName, that.lectureName) && Objects.equals(date, that.date) && Objects.equals(attendance, that.attendance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, classId, className, courseId, courseName, lectureId, lectureName, date, attendance);
    }
}
