package com.herokuapp.schoolmvc.dao;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.mapper.EmployeeMapper;
import com.herokuapp.schoolmvc.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeDAO extends JdbcDaoSupport {
 
    @Autowired
    public EmployeeDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public Employee findEmployeeById(Long id) {
        String sql = EmployeeMapper.BASE_SQL + " WHERE e.empid = ? ";
 
        Object[] params = new Object[] { id };
        EmployeeMapper mapper = new EmployeeMapper();
        try {
            Employee userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

	public void updateEmployeeRoles() {
    }
    
    public void listSalaryInstallments(){
    }

}