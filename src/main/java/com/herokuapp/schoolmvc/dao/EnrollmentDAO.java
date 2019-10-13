package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public Enrollment findEnrollmentById(Long enrollId) {
        String SQL = "SELECT * FROM Enrollment WHERE enrollid=" + enrollId;
        try{
            return this.getJdbcTemplate().queryForObject(SQL, new EnrollmentMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public Enrollment getCurrentStudentEnrollment(Long studentId) {
        String SQL = "SELECT * FROM Enrollment WHERE studentid=" + 
                      studentId.toString()  + " AND status='ONGOING'";
        try{
            return this.getJdbcTemplate().queryForObject(SQL, new EnrollmentMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Enrollment> listCurrentEnrollmentsByCourseId(Long courseId){
        String SQL = "SELECT * from Enrollment e, Course c where c.courseid = " + courseId + 
                     " AND e.level=c.level AND e.status='ONGOING'";
        try{
            return this.getJdbcTemplate().query(SQL, new EnrollmentMapper());
        } catch (DataAccessException e) {
            return new ArrayList<Enrollment>();
        }
    }

    public class EnrollmentMapper implements RowMapper<Enrollment> {
    
        public static final String BASE_SQL = "SELECT * FROM Enrollment e, User u WHERE u.userid = e.empid";
    
        @Override
        public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
    
            Long enrollId = rs.getLong("enrollid");
            Student student = studentDao.findStudentById(rs.getLong("studentid"));
            _Class _class = classDao.getClassByLevel(rs.getLong("level"));
            Status status = Status.valueOf(rs.getString("status"));
            Long year = rs.getLong("year");
            return new Enrollment(enrollId, student, _class, status, year);
        }

    } 

}