package com.herokuapp.schoolmvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.mapper.CourseMapper;
import com.herokuapp.schoolmvc.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CourseDAO extends JdbcDaoSupport {
 
    @Autowired
    public CourseDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Course> listAllCourses() {
        String sql = CourseMapper.BASE_SQL;
 
        List<Course> courses = this.getJdbcTemplate().query(sql, new CourseMapper());
 
        return courses;
    }

    public List<Course> listCoursesByTeacherId(Long id){
        String sql = CourseMapper.BASE_SQL + " WHERE c.teacherid=" + id.toString();
        
        try {
            List<Course> courses = this.getJdbcTemplate().query(sql, new CourseMapper());
            return courses;
        } catch (DataAccessException e) {
            List <Course> emptyList = new ArrayList<Course>();
            return emptyList;
        }
    }

    public List<Course> listCoursesByClass(Long level){
        String sql = CourseMapper.BASE_SQL + " WHERE c.level=" + level.toString();
        
        try {
            List<Course> courses = this.getJdbcTemplate().query(sql, new CourseMapper());
            return courses;
        } catch (DataAccessException e) {
            List <Course> emptyList = new ArrayList<Course>();
            return emptyList;
        }
    }
     
}