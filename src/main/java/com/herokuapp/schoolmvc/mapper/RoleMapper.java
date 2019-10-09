package com.herokuapp.schoolmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.herokuapp.schoolmvc.model.Role;

import org.springframework.jdbc.core.RowMapper;

public class RoleMapper implements RowMapper<Role> {
 
    public static final String BASE_SQL = "SELECT roleid, rolename FROM Role r";
 
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long roleId = rs.getLong("roleid");
        String roleName = rs.getString("rolename");
 
        return new Role(roleId, roleName);
    }
 
}