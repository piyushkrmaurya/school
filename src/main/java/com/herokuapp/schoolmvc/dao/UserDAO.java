package com.herokuapp.schoolmvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.form.UserForm;
import com.herokuapp.schoolmvc.model.User;
import com.herokuapp.schoolmvc.model.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

    @Autowired
    private EmployeeDAO employeeDao;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Autowired
    public UserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public User findUserAccount(String userName) {
        String sql = UserMapper.BASE_SQL + " WHERE u.username = ? ";
 
        Object[] params = new Object[] { userName };
        UserMapper mapper = new UserMapper();
        try {
            User userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Long createUserAccount(UserForm userForm) {
        String CREATE_SQL = "INSERT INTO User(username, password, name, gender, address, type) VALUES(?, ?, ?, ?, ?, ?)";
        String encrytedPassword = this.passwordEncoder.encode(userForm.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection
            .prepareStatement(CREATE_SQL, new String[] {"userid"});
            ps.setString(1, userForm.getUserName());
            ps.setString(2, encrytedPassword);
            ps.setString(3, userForm.getName());
            ps.setString(4, userForm.getGender());
            ps.setString(5, userForm.getAddress());
            ps.setString(6, userForm.getType().toString());
            return ps;
        }, keyHolder); 

        Long userId = keyHolder.getKey().longValue();
        
        if(userForm.getType().equals(UserType.EMPLOYEE))
            employeeDao.createEmployeeAccount(userId, userForm);
        
        if(userForm.getType().equals(UserType.STUDENT))
            studentDAO.createStudentAccount(userId, userForm);

        return userId;
    }

    public List<User> listUserAccounts() {
        String sql = UserMapper.BASE_SQL;
 
        UserMapper mapper = new UserMapper();
        try {
            List<User> users = this.getJdbcTemplate().query(sql, mapper);
            return users;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
 
    public class UserMapper implements RowMapper<User> {
 
        public static final String BASE_SQL = "SELECT userid, username, name, password, gender, address, type FROM User u";
     
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
     
            Long userId = rs.getLong("userid");
            String userName = rs.getString("username");
            String name = rs.getString("name");
            String password = rs.getString("password");
            String gender = rs.getString("gender");
            String address = rs.getString("address");
            UserType type = UserType.valueOf(rs.getString("type"));
     
            return new User(userId, userName, name, password, gender, address, type);
        }
     
    }

}