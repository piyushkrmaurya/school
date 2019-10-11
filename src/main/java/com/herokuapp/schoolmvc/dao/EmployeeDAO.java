package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.form.EmployeeForm;
import com.herokuapp.schoolmvc.model.Employee;
import com.herokuapp.schoolmvc.model.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
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
        String sql = EmployeeMapper.BASE_SQL + " AND e.empid = ? ";
 
        Object[] params = new Object[] { id };
        EmployeeMapper mapper = new EmployeeMapper();
        try {
            Employee userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void createEmployeeAccount(Long empId) {
        String CREATE_SQL = "INSERT INTO Employee(empid) VALUES(?)";
        this.getJdbcTemplate().update(CREATE_SQL, new Object[]{empId});
    }

    public void updateEmployee(Long empId, EmployeeForm empForm) {
        String UPDATE_SALARY_SQL = "UPDATE Employee SET salary=? WHERE empid=?";
        this.getJdbcTemplate().update(UPDATE_SALARY_SQL, 
            new Object[] {empForm.getSalary(), empId});
        updateRoles(empId, empForm.getRoles());
    }

    public void updateRoles(Long empId, List<Long> roles) {

        String CLEAR_ROLES = "DELETE FROM Employee_Role WHERE empid=?";
        this.getJdbcTemplate().update(CLEAR_ROLES, new Object[] {empId});

        String ASSIGN_ROLES_SQL = "INSERT INTO Employee_Role VALUES";
        Iterator<Long> roleIterator = roles.iterator();
        while (roleIterator.hasNext()) {
            Long roleId = roleIterator.next();
            ASSIGN_ROLES_SQL += String.format("(%d, %d)", empId, roleId);
            if(roleIterator.hasNext())
                ASSIGN_ROLES_SQL += ",";
        }
        
        this.getJdbcTemplate().update(ASSIGN_ROLES_SQL);
    }
    
    public void listSalaryInstallments() {
    }


    public class EmployeeMapper implements RowMapper<Employee> {
    
        public static final String BASE_SQL = "SELECT * FROM Employee e, User u WHERE u.userid = e.empid";
    
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
    
            Long empId = rs.getLong("empid");
            Long salary = rs.getLong("salary");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String address = rs.getString("address");
            UserType userType = UserType.valueOf(rs.getString("type"));

            return new Employee(empId, salary, name, gender, address, userType);
        }

    }

}