package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.TransactionsDao;
import com.techelevator.model.Transactions;

@Component
public class JdbcTransactionsDao implements TransactionsDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Transactions> transactionsRowMapper = (rs, rowNum) -> {
        Transactions transactions = new Transactions();
        transactions.setTransactionId(rs.getInt("transaction_id"));
        transactions.setSenderId(rs.getInt("sender_id"));
        transactions.setReceiverId(rs.getInt("receiver_id"));
        return transactions;
    };

    @Override
    public Transactions getTransactionById(int transactionId) {
        String sql = "SELECT * FROM Transactions WHERE transaction_id = ?";
        return jdbcTemplate.queryForObject(sql, transactionsRowMapper, transactionId);
    }

    @Override
    public List<Transactions> getAllTransactions() {
        String sql = "SELECT * FROM Transactions";
        return jdbcTemplate.query(sql, transactionsRowMapper);
    }

    @Override
    public boolean addTransaction(Transactions transactions) {
        String sql = "INSERT INTO Transactions (sender_id, receiver_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, transactions.getSenderId(), transactions.getReceiverId()) == 1;
    }

    @Override 
    public boolean updateTransaction(Transactions transactions) {
        String sql = "UPDATE Transactions SET sender_id = ?, receiver_id = ? WHERE transaction_id = ?";
        return jdbcTemplate.update(sql,
            transactions.getSenderId(),
            transactions.getReceiverId(),
            transactions.getTransactionId()
            ) == 1;
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        String sql = "DELETE FROM Transactions WHERE transaction_id = ?";
        return jdbcTemplate.update(sql, transactionId) == 1;
    }

    @Override
    public List<Transactions> getTransactionsBySenderId(int senderId) {
        String sql = "SELECT * FROM Transactions WHERE sender_id = ?";
        return jdbcTemplate.query(sql, transactionsRowMapper, senderId);
    }

    @Override
    public List<Transactions> getTransactionsByReceiverId(int receiverId) {
        String sql = "SELECT * FROM Transactions WHERE receiver_id = ?";
        return jdbcTemplate.query(sql, transactionsRowMapper, receiverId);
    }
}
