package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.form.EnrollmentForm;

import com.herokuapp.schoolmvc.model.Enrollment;
import com.herokuapp.schoolmvc.model.Grade;
import com.herokuapp.schoolmvc.model.Status;
import com.herokuapp.schoolmvc.model.Student;
import com.herokuapp.schoolmvc.model._Class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class EnrollmentDAO extends JdbcDaoSupport {

    @Autowired
    public EnrollmentDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Autowired
    private ClassDAO classDao; 

    @Autowired
    private StudentDAO studentDao; 

    public Enrollment getCurrentStudentEnrollment(Long studentId) {
        String SQL = "SELECT * FROM Enrollment WHERE studentid=" + 
                      studentId.toString()  + " AND status='ONGOING'";
        try{
            return this.getJdbcTemplate().queryForObject(SQL, new EnrollmentMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void enrollStudent(Long studentId, Long level){
        String SQL = "INSERT INTO Enrollment(studentid, level, status) VALUES(?, ?, ?)";

        this.getJdbcTemplate().update(SQL, new Object[] {studentId, level, "ONGOING"});
    }

    public class EnrollmentMapper implements RowMapper<Enrollment> {
    
        public static final String BASE_SQL = "SELECT * FROM Enrollment e, User u WHERE u.userid = e.empid";
    
        @Override
        public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
    
            Long enrollId = rs.getLong("enrollid");
            Student student = studentDao.findStudentById(rs.getLong("studentid"));
            _Class _class = classDao.getClassByLevel(rs.getLong("level"));
            Status status = Status.valueOf(rs.getString("status"));
            return new Enrollment(enrollId, student, _class, status);
        }

    } 

}