package com.example.demo.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class", schema = "MTD_attend", catalog = "")
public class Clazz {
    private int classId;
    private String className;
    private int education_id;

    @Id
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
    @Column(name = "education_id")
    public int getEducation_id() {
        return education_id;
    }

    public void setEducation_id(int education_id) {
        this.education_id = education_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz that = (Clazz) o;
        return classId == that.classId && Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, className);
    }
}
