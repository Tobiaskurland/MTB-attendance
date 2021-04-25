package com.example.demo.Model.Views;

import javax.persistence.*;

@Entity
@Table(name = "attendance_view", schema = "MTD_attend", catalog = "")
public class AttendanceView {
    private int attendanceViewID;
    private int userID;
    private String first_name;
    private String last_name;
    private int classID;
    private String class_name;
    private int courseID;
    private String course_name;
    private String lecture_name;
    private String date;
    private String lecture_status;
    private String first;
    private String second;
    private String third;
    private String fourth;
    private String fifth;
    private String sixth;

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
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Basic
    @Column(name = "last_name")
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Basic
    @Column(name = "class_id")
    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    @Basic
    @Column(name = "class_name")
    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Basic
    @Column(name = "course_id")
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Basic
    @Column(name = "course_name")
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    @Basic
    @Column(name = "lecture_name")
    public String getLecture_name() {
        return lecture_name;
    }

    public void setLecture_name(String lecture_name) {
        this.lecture_name = lecture_name;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "lecture_status")
    public String getLecture_status() {
        return lecture_status;
    }

    public void setLecture_status(String lecture_status) {
        this.lecture_status = lecture_status;
    }

    @Basic
    @Column(name = "first")
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Basic
    @Column(name = "second")
    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Basic
    @Column(name = "third")
    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    @Basic
    @Column(name = "fourth")
    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    @Basic
    @Column(name = "fifth")
    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    @Basic
    @Column(name = "sixth")
    public String getSixth() {
        return sixth;
    }

    public void setSixth(String sixth) {
        this.sixth = sixth;
    }
}
