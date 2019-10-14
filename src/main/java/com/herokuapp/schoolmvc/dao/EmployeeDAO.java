package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.form.EmployeeForm;
import com.herokuapp.schoolmvc.form.UserForm;
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

    @Autowired
    private TeacherDAO teacherDao;

    public List<Employee> listAllEmployees() {
        String sql = EmployeeMapper.BASE_SQL;
 
        try {
            return this.getJdbcTemplate().query(sql, new EmployeeMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Employee>();
        }
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

    public void createEmployeeAccount(Long empId, UserForm userForm) {
        String CREATE_SQL = String.format(
        "INSERT INTO Employee SELECT * FROM (SELECT %d, %d) As e"+
        " WHERE NOT EXISTS (SELECT * FROM Employee WHERE empid=%d)",
        empId, userForm.getSalary(), empId
        );
        this.getJdbcTemplate().update(CREATE_SQL);
        updateRoles(empId, userForm.getRoles());
        if(userForm.getRoles().contains(Long.valueOf(1)))
            teacherDao.createTeacherAccount(empId, userForm);
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
            if(roleIterator.hasNext()) ASSIGN_ROLES_SQL += ",";
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
            String userName = rs.getString("username");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String address = rs.getString("address");
            UserType userType = UserType.valueOf(rs.getString("type"));

            return new Employee(empId, salary, userName, name, gender, address, userType);
        }

    }

}