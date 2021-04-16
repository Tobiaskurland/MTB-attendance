package com.example.demo.Model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "lecture", schema = "MTD_attend", catalog = "")
public class LectureEntity {
    private int lectureId;
    private String lectureName;
    private Date date;
    private int timeInterval;
    private String verificationCode;
    private Timestamp codeExpire;
    private int courseId;

    @Id
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
    @Column(name = "time_interval")
    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Basic
    @Column(name = "verification_code")
    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Basic
    @Column(name = "code_expire")
    public Timestamp getCodeExpire() {
        return codeExpire;
    }

    public void setCodeExpire(Timestamp codeExpire) {
        this.codeExpire = codeExpire;
    }

    @Basic
    @Column(name = "course_id")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LectureEntity that = (LectureEntity) o;

        if (lectureId != that.lectureId) return false;
        if (timeInterval != that.timeInterval) return false;
        if (courseId != that.courseId) return false;
        if (lectureName != null ? !lectureName.equals(that.lectureName) : that.lectureName != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (verificationCode != null ? !verificationCode.equals(that.verificationCode) : that.verificationCode != null)
            return false;
        if (codeExpire != null ? !codeExpire.equals(that.codeExpire) : that.codeExpire != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lectureId;
        result = 31 * result + (lectureName != null ? lectureName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + timeInterval;
        result = 31 * result + (verificationCode != null ? verificationCode.hashCode() : 0);
        result = 31 * result + (codeExpire != null ? codeExpire.hashCode() : 0);
        result = 31 * result + courseId;
        return result;
    }
}
