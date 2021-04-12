package com.example.demo.Model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "lecture", schema = "MTD_attend", catalog = "")
public class LectureEntity {
    private Long id;
    private int lectureId;
    private String lectureName;
    private Date date;
    private int timeInterval;
    private String verificationCode;
    private Timestamp codeExpire;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "lectureID")
    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    @Basic
    @Column(name = "lectureName")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureEntity that = (LectureEntity) o;
        return lectureId == that.lectureId && timeInterval == that.timeInterval && Objects.equals(lectureName, that.lectureName) && Objects.equals(date, that.date) && Objects.equals(verificationCode, that.verificationCode) && Objects.equals(codeExpire, that.codeExpire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lectureId, lectureName, date, timeInterval, verificationCode, codeExpire);
    }
}
