package com.example.demo.Repository;

import com.example.demo.Model.UserEntity;
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
    public UserEntity findLogin(String firstName, String password) {
        String sql = "SELECT first_name, last_name, email, class_id FROM `User` WHERE first_name = ? AND password = ?";
        UserEntity u = this.jdbc.query(sql, resultSet -> {
            UserEntity user = new UserEntity();
            while (resultSet.next()){
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
