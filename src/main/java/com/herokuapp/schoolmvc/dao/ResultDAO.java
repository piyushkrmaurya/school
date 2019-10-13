package com.herokuapp.schoolmvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.form.ResultForm;
import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.Enrollment;
import com.herokuapp.schoolmvc.model.Grade;
import com.herokuapp.schoolmvc.model.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ResultDAO extends JdbcDaoSupport {
 
    @Autowired
    public ResultDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Autowired
    private CourseDAO courseDao; 

    @Autowired
    private EnrollmentDAO enrollDao;

    public List<Result> getResultsByStudentIdAndClass(Long studentId, Long level) {
        String SQL = "SELECT * FROM Result r, Enrollment e WHERE e.studentid = ? AND e.level = ? AND r.enrollid = e.enrollid";
        return this.getJdbcTemplate().query(SQL, new ResultMapper());
    }

    public Result getResultByEnrollmentIdAndCourseId(Long enrollId, Long courseId) {
        String SQL = "SELECT * FROM Result WHERE enrollid = ? AND courseid = ?";
        return this.getJdbcTemplate().queryForObject(SQL, new ResultMapper());
    }

    public void createResultForCourse(Long courseId, ResultForm resultForm) {
        String SQL = "INSERT INTO Result(enrollid, courseid, grade) VALUES(?, ?, ?)";
        this.getJdbcTemplate().batchUpdate(
        SQL,
        new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, resultForm.getResults().get(i).getEnrollId());
                ps.setLong(2, courseId);
                ps.setString(3, resultForm.getResults().get(i).getGrade().toString());
            }
            @Override
            public int getBatchSize() {
                return resultForm.getResults().size();
            }
        });
    }

    public class ResultMapper implements RowMapper<Result> {
 
        public static final String BASE_SQL = "SELECT * FROM Result r";
     
        @Override
        public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
    
            Enrollment enrollment = enrollDao.findEnrollmentById(rs.getLong("enrollid"));
            Course course = courseDao.findCourseById(rs.getLong("courseid"));
            String name = rs.getString("name");
            Grade grade = Grade.valueOf(rs.getString("grade"));

            return new Result(enrollment, grade, course);
        }
     
    }
    
}