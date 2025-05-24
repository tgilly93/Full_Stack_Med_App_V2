package com.techelevator.jdbcDao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.MessagesDao;
import com.techelevator.model.Messages;

@Component
public class JdbcMessagesDao implements MessagesDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMessagesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Messages> messagesRowMapper = (rs, rowNum) -> {
        Messages message = new Messages();
        message.setMessageId(rs.getInt("message_id"));
        message.setSenderId(rs.getInt("sender_id"));
        message.setReceiverId(rs.getInt("receiver_id"));
        message.setMessageContent(rs.getString("message_content"));
        message.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        message.setMessageType(rs.getString("message_type"));
        message.setSenderDeleted(rs.getBoolean("sender_deleted"));
        message.setReceiverDeleted(rs.getBoolean("receiver_deleted"));

        return message;
    };

    @Override
    public Messages getMessageById(int messageId) {
        String sql = "SELECT * FROM Messages WHERE message_id = ?";

        return jdbcTemplate.queryForObject(sql, messagesRowMapper, messageId);
    }

    @Override
    public List<Messages> getMessagesForUser(int userId) {
        String sql = "SELECT * FROM Messages WHERE (sender_id = ? AND sender_deleted = false) OR (receiver_id = ? AND receiver_deleted = false) ORDER BY date_time DESC";

        return jdbcTemplate.query(sql, messagesRowMapper, userId, userId);
    }

    @Override
    public boolean sendMessage(Messages message) {
        String sql = "INSERT INTO Messages (sender_id, receiver_id, message_content, date_time, message_type, sender_deleted, receiver_deleted) VALUES (?, ?, ?, ?, ?, false, false)";

        int rowsAffected = jdbcTemplate.update(sql,
                message.getSenderId(),
                message.getReceiverId(),
                message.getMessageContent(),
                Timestamp.valueOf(message.getDateTime()),
                message.getMessageType());

        return rowsAffected > 0;
    }

    @Override
    public boolean softDeleteMessage(int messageId, int userId) {
        String senderCheckSql = "SELECT COUNT(*) FROM Messages WHERE message_id = ? AND sender_id = ?";
        String receiverCheckSql = "SELECT COUNT(*) FROM Messages WHERE message_id = ? AND receiver_id = ?";

        int isSender = jdbcTemplate.queryForObject(senderCheckSql, Integer.class, messageId, userId);

        int isReceiver = jdbcTemplate.queryForObject(receiverCheckSql, Integer.class, messageId, userId);

        if (isSender > 0) {
            String sql = "UPDATE Messages SET sender_deleted = true WHERE message_id = ?";
            return jdbcTemplate.update(sql, messageId) > 0;
        } else if (isReceiver > 0) {
            String sql = "UPDATE Messages SET receiver_deleted = true WHERE message_id = ?";
            return jdbcTemplate.update(sql, messageId) > 0;
        }
        return false;
    }

    @Override
    public boolean hardDeleteMessage(int messageId) {
        String sql = "DELETE FROM Messages WHERE message_id = ?";
        return jdbcTemplate.update(sql, messageId) > 0;
    }
}
