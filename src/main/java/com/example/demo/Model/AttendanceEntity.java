package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "attendance", schema = "MTD_attend", catalog = "")
public class AttendanceEntity {
    private int attendanceId;
    private int userId;
    private int lectureId;

    @Id
    @Column(name = "attendanceID")
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "lecture_id")
    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendanceEntity that = (AttendanceEntity) o;

        if (attendanceId != that.attendanceId) return false;
        if (userId != that.userId) return false;
        if (lectureId != that.lectureId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attendanceId;
        result = 31 * result + userId;
        result = 31 * result + lectureId;
        return result;
    }
}
