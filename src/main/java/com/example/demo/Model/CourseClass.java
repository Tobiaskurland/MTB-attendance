package com.example.demo.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course_class", schema = "MTD_attend", catalog = "")
public class CourseClass {
    private int idcourseClass;
    private int course_id;
    private int class_id;

    @Id
    @Column(name = "idcourse_class")
    public int getIdcourseClass() {
        return idcourseClass;
    }

    public void setIdcourseClass(int idcourseClass) {
        this.idcourseClass = idcourseClass;
    }

    @Basic
    @Column(name = "course_id")
    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Basic
    @Column(name = "class_id")
    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseClass that = (CourseClass) o;
        return idcourseClass == that.idcourseClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcourseClass);
    }
}
