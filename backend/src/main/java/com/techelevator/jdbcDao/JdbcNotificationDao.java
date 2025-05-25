package com.techelevator.jdbcDao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.NotificationDao;
import com.techelevator.model.Notification;

@Component
public class JdbcNotificationDao implements NotificationDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcNotificationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Notification> notificationRowMapper = (rs, rowNum) -> {
        Notification notification = new Notification();
        notification.setNotificationId(rs.getInt("notification_id"));
        notification.setUserId(rs.getInt("user_id"));
        notification.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        notification.setNotificationContent(rs.getString("notification_content"));
        notification.setNotificationStatus(rs.getString("notification_status"));

        return notification;
    };

    @Override
    public List<Notification> getNotificationsByUserId(int userId) {
        String sql = "SELECT * FROM Notification WHERE user_id = ? ORDER BY date_time DESC";
        return jdbcTemplate.query(sql, notificationRowMapper, userId);
    }

    @Override
    public Notification getNotificationById(int notificationId) {
        String sql = "SELECT * FROM Notification WHERE notification_id = ?";
        return jdbcTemplate.queryForObject(sql, notificationRowMapper, notificationId);
    }

    @Override
    public boolean createNotification(Notification notification) {
        String sql = "INSERT INTO Notification (user_id, date_time, notification_content, notification_status) VALUES (?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql, 
            notification.getUserId(),
            Timestamp.valueOf(notification.getDateTime()),
            notification.getNotificationContent(),
            notification.getNotificationStatus());

        return rowsAffected > 0;
    }

    @Override
    public boolean updateNotificationStatus(int notificationId, String notificationStatus) {
        String sql = "UPDATE Notification SET notification_status = ? WHERE notification_id = ?";
        return jdbcTemplate.update(sql, notificationStatus, notificationId) > 0;
    }

    @Override
    public boolean deleteNotification(int notificationId) {
        String sql = "DELETE FROM Notification WHERE notification_id = ?";
        return jdbcTemplate.update(sql, notificationId) > 0;
    }
}
