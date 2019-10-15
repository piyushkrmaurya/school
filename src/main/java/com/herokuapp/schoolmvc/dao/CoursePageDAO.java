package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.CoursePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CoursePageDAO extends JdbcDaoSupport {

    @Autowired
    public CoursePageDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Autowired
    private CourseDAO courseDao;

    public CoursePage findCoursePageById(Long id){
        String sql = CoursePageMapper.BASE_SQL + " WHERE c.courseid=" + id;
        try{
            return this.getJdbcTemplate().queryForObject(sql, new CoursePageMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public CoursePage findLastestCoursePageByCourseId(Long courseId){
        String sql = CoursePageMapper.BASE_SQL + " WHERE c.courseid=" + courseId + " AND year >= ALL (SELECT year FROM CoursePage WHERE courseid="+ courseId +")";
        try{
            return this.getJdbcTemplate().queryForObject(sql, new CoursePageMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<CoursePage> listAllCoursePagees() {
        String sql = CoursePageMapper.BASE_SQL;
 
        List<CoursePage> classes = this.getJdbcTemplate().query(sql, new CoursePageMapper());
 
        return classes;
    }

    public class CoursePageMapper implements RowMapper<CoursePage> {
 
        public static final String BASE_SQL = "SELECT * FROM CoursePage c";
     
        @Override
        public CoursePage mapRow(ResultSet rs, int rowNum) throws SQLException {
     
            Long cpId = rs.getLong("cpid");
            Course course = courseDao.findCourseById(rs.getLong("courseid"));
            Long year = rs.getLong("year");
            Boolean active = rs.getBoolean("active");
     
            return new CoursePage(cpId, course, year, active);
        }
     
    }
     
}