package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.TransactionsDao;
import com.techelevator.model.Transactions;

@Service
public class TransactionsService {
    private final TransactionsDao transactionsDao;

    public TransactionsService(TransactionsDao transactionsDao) {
        this.transactionsDao = transactionsDao;
    }

    public Transactions getTransactionsById(int transactionId) {
        return transactionsDao.getTransactionById(transactionId);
    }

    public List<Transactions>getAllTransactions() {
        return transactionsDao.getAllTransactions();
    }

    public boolean addTransaction(Transactions transactions) {
        return transactionsDao.addTransaction(transactions);
    }

    public boolean updateTransaction(Transactions transactions) {
        return transactionsDao.updateTransaction(transactions);
    }

    public boolean deleteTransaction(int transactionId) {
        return transactionsDao.deleteTransaction(transactionId);
    }

    public List<Transactions> getTransactionsBySenderId(int senderId) {
        return transactionsDao.getTransactionsBySenderId(senderId);
    }

    public List<Transactions> getTransactionByReceiverId(int receiverId) {
        return transactionsDao.getTransactionsByReceiverId(receiverId);
    }
}
