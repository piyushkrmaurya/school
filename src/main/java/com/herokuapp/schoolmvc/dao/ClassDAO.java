package com.herokuapp.schoolmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.model._Class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ClassDAO extends JdbcDaoSupport {
 
    @Autowired
    public ClassDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public _Class getClassByLevel(Long level){
        String sql = ClassMapper.BASE_SQL + " WHERE c.level=" + level.toString();
        return this.getJdbcTemplate().queryForObject(sql, new ClassMapper());
    }

    public _Class getCurrentClassByStudentId(Long studentId) {
        String sql = "SELECT * FROM Class c, Enrollment e WHERE c.level = e.level" + 
        " AND e.studentid =  "+ studentId +" AND e.status = 'ONGOING' ";
        return this.getJdbcTemplate().queryForObject(sql, new ClassMapper());
    }

    public List<_Class> listAllClasses() {
        String sql = ClassMapper.BASE_SQL;
 
        List<_Class> classes = this.getJdbcTemplate().query(sql, new ClassMapper());
 
        return classes;
    }

    public class ClassMapper implements RowMapper<_Class> {
 
        public static final String BASE_SQL = "SELECT * FROM Class c";
     
        @Override
        public _Class mapRow(ResultSet rs, int rowNum) throws SQLException {
     
            Long level = rs.getLong("level");
     
            return new _Class(level);
        }
     
    }
     
}