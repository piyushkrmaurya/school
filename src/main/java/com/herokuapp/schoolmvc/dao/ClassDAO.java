package com.herokuapp.schoolmvc.dao;

import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.mapper.ClassMapper;
import com.herokuapp.schoolmvc.model._Class;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<_Class> listAllClasses() {
        String sql = ClassMapper.BASE_SQL;
 
        List<_Class> classes = this.getJdbcTemplate().query(sql, new ClassMapper());
 
        return classes;
    }
     
}