package com.herokuapp.schoolmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.herokuapp.schoolmvc.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

public class TeacherMapper implements RowMapper<Teacher> {
 
    public static final String BASE_SQL = "SELECT teacherid, qualification FROM Teacher t";
 
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long teacherId = rs.getLong("teacherid");
        String qualification = rs.getString("qualification");

        return new Teacher(teacherId, qualification);
    }
 
}