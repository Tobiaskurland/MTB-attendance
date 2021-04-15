package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "education", schema = "AttendMeDaddy", catalog = "")
public class EducationEntity {
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

        EducationEntity that = (EducationEntity) o;

        if (educationId != that.educationId) return false;
        if (educationName != null ? !educationName.equals(that.educationName) : that.educationName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = educationId;
        result = 31 * result + (educationName != null ? educationName.hashCode() : 0);
        return result;
    }
}
