package com.herokuapp.schoolmvc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.model.Employee;
import com.herokuapp.schoolmvc.model.Month;
import com.herokuapp.schoolmvc.model.Salary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SalaryDAO extends JdbcDaoSupport {

    @Autowired
    public SalaryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Autowired
    private EmployeeDAO employeeDao;

    public List<Salary> listSalaryInstallments(){
        String SQL = SalaryMapper.BASE_SQL;
        return this.getJdbcTemplate().query(SQL, new SalaryMapper());
    }

    public List<Salary> listSalaryInstallmentsByEmpID(Long empId){
        String SQL = SalaryMapper.BASE_SQL + " WHERE empId = " + empId;
        return this.getJdbcTemplate().query(SQL, new SalaryMapper());
    }

    public void newSalaryInstallment(Long empId, Long manger, Long amount, Month month, Long year){
        String SQL = "INSERT INTO Salary(empid, manager, amount, month, year, date) VALUES(?, ?, ?, ?, ?, ?)";

        this.getJdbcTemplate().update(SQL);
    }

    public void nextSalaryInstallments(Long manager, String month, Long year){
        String SQL = "INSERT INTO Salary(empid, manager, amount, month, year, date) VALUES(?, ?, ?, ?, ?, ?)";

        List <Employee> employees = employeeDao.listAllEmployees();

        this.getJdbcTemplate().batchUpdate(
        SQL,
        new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, employees.get(i).getEmpId());
                ps.setLong(2, manager);
                ps.setLong(3, employees.get(i).getSalary());
                ps.setString(4, month);
                ps.setLong(5, year);
                ps.setDate(6, new java.sql.Date((new java.util.Date()).getTime()));
            }
            @Override
            public int getBatchSize() {
                return employees.size();
            }
        });
    }

    public class SalaryMapper implements RowMapper<Salary> {
 
        public static final String BASE_SQL = "SELECT * FROM Salary s";
     
        @Override
        public Salary mapRow(ResultSet rs, int rowNum) throws SQLException {

            Long salaryId = rs.getLong("salaryid");
            Employee employee = employeeDao.findEmployeeById(rs.getLong("empid"));
            Employee manager = employeeDao.findEmployeeById(rs.getLong("manager"));
            Long amount = rs.getLong("amount");
            Month month = Month.valueOf(rs.getString("month"));
            Long year = rs.getLong("year");
            Date date = rs.getDate("date");

            return new Salary(salaryId, employee, manager, amount, month, year, date);
        }
     
    }
}