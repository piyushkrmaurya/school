package com.herokuapp.schoolmvc.dao;

import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.mapper.TeacherMapper;
import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        String sql = TeacherMapper.BASE_SQL + " WHERE t.teacherid=" + id.toString();
        try {
            return this.getJdbcTemplate().queryForObject(sql, new TeacherMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void assignCourses(Long id, List<Course> courses){
        
    }

}