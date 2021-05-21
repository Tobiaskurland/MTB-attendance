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
    public User findLogin(String email, String password) {
        String sql = "SELECT userID, first_name, last_name, email, role_id FROM `user` WHERE email = ? AND password = ?";
        User u = this.jdbc.query(sql, resultSet -> {
            User user = new User();
            while (resultSet.next()){
                user.setRole_id(resultSet.getInt("role_id"));
                user.setUserId(resultSet.getInt("userID"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setRole_id((resultSet.getInt("role_id")));
                return user;
            }
            return null;
        }, email, password);
        return u;
    }
}
