package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.model.Student;
import com.herokuapp.schoolmvc.model.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentDAO extends JdbcDaoSupport {
 
    @Autowired
    public StudentDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public Student findStudentById(Long id) {
        String sql = StudentMapper.BASE_SQL + " AND s.studentid = ? ";
 
        Object[] params = new Object[] { id };
        StudentMapper mapper = new StudentMapper();
        try {
            Student userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    
    public void listFeeInstallments() {
    }


    public class StudentMapper implements RowMapper<Student> {
    
        public static final String BASE_SQL = "SELECT * FROM Student s, User u WHERE u.userid = s.studentid";
    
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
    
            Long studentId = rs.getLong("studentid");
            String userName = rs.getString("username");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String address = rs.getString("address");
            UserType userType = UserType.valueOf(rs.getString("type"));

            return new Student(studentId, userName, name, gender, address, userType);
        }

    }

}