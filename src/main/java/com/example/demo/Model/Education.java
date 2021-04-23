package com.example.demo.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "education", schema = "MTD_attend", catalog = "")
public class Education {
    private int educationId;
    private String educationName;

    @Id
    @Column(name = "educationID")
    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    @Basic
    @Column(name = "education_name")
    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education that = (Education) o;
        return educationId == that.educationId && Objects.equals(educationName, that.educationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(educationId, educationName);
    }
}
