package com.example.demo.Repository;

import com.example.demo.Model.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserClassRepo extends JpaRepository<UserClass, Integer> {

    @Query(value = "SELECT * FROM user_class uc " +
            "INNER JOIN `user` u on uc.user_id = userID " +
            "WHERE uc.class_id = ?1 AND u.role_id = 1", nativeQuery = true)
    List<UserClass> findAllStudentInClass(int classId);

}
