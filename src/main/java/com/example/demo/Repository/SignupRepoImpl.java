package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignupRepoImpl implements SignupRepo{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public void signup(User u) {

        String sql = "INSERT INTO `user` (userID, firstName, lastName, email, password) Values (default, ?, ?, ?, ?)";
        jdbc.update(sql, u.getFirstName(), u.getLastName(),
                u.getEmail(), u.getPassword());
    }
}
