package com.herokuapp.schoolmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.herokuapp.schoolmvc.model.Employee;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee> {
 
    public static final String BASE_SQL = "SELECT empid, salary FROM Employee e";
 
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long empId = rs.getLong("empid");
        Long salary = rs.getLong("salary");

        return new Employee(empId, salary);
    }
 
}