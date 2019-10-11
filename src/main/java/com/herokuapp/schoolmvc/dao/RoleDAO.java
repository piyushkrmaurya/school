package com.herokuapp.schoolmvc.dao;

import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.mapper.RoleMapper;
import com.herokuapp.schoolmvc.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport {
 
    @Autowired
    public RoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Role> getAllRoles() {
        String sql = RoleMapper.BASE_SQL;
 
        List<Role> roles = this.getJdbcTemplate().query(sql, new RoleMapper());
 
        return roles;
    }
 
    public List<Role> getAssignedRoles(Long empId) {
        String sql = "SELECT r.roleid, r.rolename "
                + " FROM Employee_Role er, Role r "
                + " WHERE er.roleid = r.roleid AND er.empid = ? ";
 
        Object[] params = new Object[] { empId };
 
        List<Role> roles = this.getJdbcTemplate().query(sql, params, new RoleMapper());
 
        return roles;
    }
    
}