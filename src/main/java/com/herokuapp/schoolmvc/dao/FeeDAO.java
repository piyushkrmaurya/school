package com.herokuapp.schoolmvc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.model.Employee;
import com.herokuapp.schoolmvc.model.Month;
import com.herokuapp.schoolmvc.model.Student;
import com.herokuapp.schoolmvc.model.Fee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FeeDAO extends JdbcDaoSupport {

    @Autowired
    public FeeDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Autowired
    private EmployeeDAO employeeDao;

    @Autowired
    private StudentDAO studentDao;

    public List<Fee> listFeePayments(){
        String SQL = "SELECT * FROM Fee";
        return this.getJdbcTemplate().query(SQL, new FeeMapper());
    }

    public List<Fee> listFeePaymentsByStudntId(Long studentId){
        String SQL = "SELECT * FROM Fee WHERE studentid = " + studentId;
        return this.getJdbcTemplate().query(SQL, new FeeMapper());
    }


    public void newFeePayment(Long studentId, Long manager, Long amount, String month, Long year){
        String SQL = "INSERT INTO Fee(studentId, manager, amount, month, year, date) VALUES(?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[] {
            studentId,
            manager,
            amount,
            month,
            year,
            new java.sql.Date((new java.util.Date()).getTime())
        };
        this.getJdbcTemplate().update(SQL, params);
    }

    public class FeeMapper implements RowMapper<Fee> {
 
        public static final String BASE_SQL = "SELECT * FROM Fee f";
     
        @Override
        public Fee mapRow(ResultSet rs, int rowNum) throws SQLException {

            Long feeId = rs.getLong("feeid");
            Student student = studentDao.findStudentById(rs.getLong("studentid"));
            Employee manager = employeeDao.findEmployeeById(rs.getLong("manager"));
            Long amount = rs.getLong("amount");
            Month month = Month.valueOf(rs.getString("month"));
            Long year = rs.getLong("year");
            Date date = rs.getDate("date");

            return new Fee(feeId, student, manager, amount, month, year, date);
        }
     
    }
}