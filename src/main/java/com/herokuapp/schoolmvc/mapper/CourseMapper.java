package com.herokuapp.schoolmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.herokuapp.schoolmvc.model.Course;
import org.springframework.jdbc.core.RowMapper;

public class CourseMapper implements RowMapper<Course> {
 
    public static final String BASE_SQL = "SELECT * FROM Course c";
 
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long courseId = rs.getLong("courseid");
        String name = rs.getString("name");
        Long _class = rs.getLong("level");
        Long teacherId = rs.getLong("teacherid");

        return new Course(courseId, name, _class, teacherId);
    }
 
}