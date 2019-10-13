package com.herokuapp.schoolmvc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.form.NotificationForm;
import com.herokuapp.schoolmvc.model.CoursePage;
import com.herokuapp.schoolmvc.model.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NotificationDAO extends JdbcDaoSupport {
 
    @Autowired
    public NotificationDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    @Autowired
    CoursePageDAO coursePageDao;

    public List<Notification> listAllNotifications() {
        String sql = NotificationMapper.BASE_SQL + " ORDER BY noteid DESC";
 
        List<Notification> notifications = this.getJdbcTemplate().query(sql, new NotificationMapper());
 
        return notifications;
    }

    public List<Notification> listNotificationsByCpId(Long cpId) {
        String sql = NotificationMapper.BASE_SQL + " WHERE n.cpid=" + cpId;
 
        List<Notification> notifications = this.getJdbcTemplate().query(sql, new NotificationMapper());
 
        return notifications;
    }

    

    public void newNotification(NotificationForm notificationForm){
        String sql = "INSERT INTO Notification(cpid, text, date) VALUES(?, ?, ?)";
        Object[] params = new Object[] {notificationForm.getCpId(), notificationForm.getText(), notificationForm.getDate()};
        this.getJdbcTemplate().update(sql, params);
    }

    public class NotificationMapper implements RowMapper<Notification> {
 
        public static final String BASE_SQL = "SELECT * FROM Notification n";
     
        @Override
        public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
     
            Long noteId = rs.getLong("noteid");
            CoursePage coursePage = coursePageDao.findCoursePageById(rs.getLong("cpid"));  
            String text = rs.getString("text");
            Date date = rs.getDate("date");
     
            return new Notification(noteId, coursePage, text, date);
        }
     
    }
    
}