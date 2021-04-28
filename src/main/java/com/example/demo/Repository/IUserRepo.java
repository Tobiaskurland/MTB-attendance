package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userId = ?1")
    User findUserById(int id);

    @Query(value = "select u.* from course_class cc " +
            "join user_class uc on uc.class_id = cc.class_id " +
            "join user u on uc.user_id = u.userID " +
            "where cc.class_id = ?1 and cc.course_id = ?2 and u.role_id = 1", nativeQuery = true)
    List<User> findUsersByClassAndCourse(int class_id, int course_id);
}
