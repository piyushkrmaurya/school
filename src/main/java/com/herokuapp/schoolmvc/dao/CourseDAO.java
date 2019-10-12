package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.form.CourseForm;
import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.Teacher;
import com.herokuapp.schoolmvc.model._Class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
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

    @Autowired
    private ClassDAO classDao;

    @Autowired
    private TeacherDAO teacherDao;

    public List<Course> listAllCourses() {
        String sql = CourseMapper.BASE_SQL;
 
        List<Course> courses = this.getJdbcTemplate().query(sql, new CourseMapper());
 
        return courses;
    }

    public Course findCoursesById(Long id){
        String sql = CourseMapper.BASE_SQL + " WHERE c.courseid=" + id.toString();
        
        try {
            return this.getJdbcTemplate().queryForObject(sql, new CourseMapper());
        } catch (DataAccessException e) {
            return null;
        }
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

    public void createCourse(CourseForm courseForm){
        String CREATE_SQL = "INSERT INTO Course(name, level, teacherid) VALUES(?, ?, ?)";
        Object[] params = new Object[] {courseForm.getName(), courseForm.getLevel(), courseForm.getTeacherId()};
        this.getJdbcTemplate().update(CREATE_SQL, params);
    }

    public class CourseMapper implements RowMapper<Course> {
 
        public static final String BASE_SQL = "SELECT * FROM Course c";
     
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
    
            Long courseId = rs.getLong("courseid");
            String name = rs.getString("name");
            _Class _class =  classDao.getClassByLevel(rs.getLong("level"));
            Teacher teacher = teacherDao.findTeacherById(rs.getLong("teacherid"));
            return new Course(courseId, name, _class, teacher);
        }
     
    }
     
}