package com.herokuapp.schoolmvc.dao;

import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.mapper.TeacherMapper;
import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String sql = TeacherMapper.BASE_SQL + " WHERE e.empid = ? ";
 
        Object[] params = new Object[] { id };
        TeacherMapper mapper = new TeacherMapper();
        try {
            Teacher userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void assignCourses(Long id, List<Course> courses){
        
    }

}