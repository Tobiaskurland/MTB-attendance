package com.example.demo.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_class", schema = "MTD_attend", catalog = "")
public class UserClass {
    private int iduserClass;
    private int user_id;
    private int class_id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "iduser_class")
    public int getIduserClass() {
        return iduserClass;
    }

    public void setIduserClass(int iduserClass) {
        this.iduserClass = iduserClass;
    }

    @Basic
    @Column(name = "user_id")
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
        UserClass userClass = (UserClass) o;
        return iduserClass == userClass.iduserClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduserClass);
    }
}
