package com.herokuapp.schoolmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.herokuapp.schoolmvc.model.User;
import com.herokuapp.schoolmvc.model.UserType;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {
 
    public static final String BASE_SQL = "SELECT userid, username, name, password, gender, address, type FROM User u";
 
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("userid");
        String userName = rs.getString("username");
        String name = rs.getString("name");
        String password = rs.getString("password");
        String gender = rs.getString("gender");
        String address = rs.getString("address");
        UserType type = UserType.valueOf(rs.getString("type"));
 
        return new User(userId, userName, name, password, gender, address, type);
    }
 
}