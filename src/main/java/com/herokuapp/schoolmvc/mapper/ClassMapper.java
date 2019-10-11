package com.herokuapp.schoolmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.herokuapp.schoolmvc.model._Class;

import org.springframework.jdbc.core.RowMapper;

public class ClassMapper implements RowMapper<_Class> {
 
    public static final String BASE_SQL = "SELECT * FROM Class c";
 
    @Override
    public _Class mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long level = rs.getLong("level");
 
        return new _Class(level);
    }
 
}