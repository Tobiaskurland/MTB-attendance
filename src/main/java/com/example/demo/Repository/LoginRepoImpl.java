package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LoginRepoImpl implements LoginRepo{

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public User findLogin(String firstName, String password) {
        String sql = "SELECT userId, first_name, last_name, email, class_id FROM `user` WHERE first_name = ? AND password = ?";
        User u = this.jdbc.query(sql, resultSet -> {
            User user = new User();
            while (resultSet.next()){
                user.setUserId(resultSet.getInt("userId"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setClassId(resultSet.getInt("class_id"));
                return user;
            }
            return null;
        }, firstName, password);
        return u;
    }
}
