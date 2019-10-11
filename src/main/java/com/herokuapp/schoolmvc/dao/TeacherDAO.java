package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.Teacher;
import com.herokuapp.schoolmvc.model.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TeacherDAO extends JdbcDaoSupport {
 
    @Autowired
    public TeacherDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public Teacher findTeacherById(Long id) {
        String sql = TeacherMapper.BASE_SQL + " AND t.teacherid=" + id.toString();
        try {
            return this.getJdbcTemplate().queryForObject(sql, new TeacherMapper());
        } catch (DataAccessException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public void assignCourses(Long id, List<Course> courses){
        
    }

    public class TeacherMapper implements RowMapper<Teacher> {
 
        public static final String BASE_SQL = "SELECT * FROM Teacher t, Employee e, User u WHERE t.teacherid = u.userid AND t.teacherid = e.empid";
     
        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
     
            Long teacherId = rs.getLong("teacherid");
            String qualifications = rs.getString("qualifications");
            Long salary = rs.getLong("salary");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String address = rs.getString("address");
            UserType userType = UserType.valueOf(rs.getString("type"));

            return new Teacher(teacherId, qualifications, salary, name, gender, address, userType);
        }
     
    }

}