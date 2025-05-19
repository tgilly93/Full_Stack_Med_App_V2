package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Transactions;

public interface TransactionsDao {
    Transactions getTransactionById(int transactionId);

    List<Transactions> getAllTransactions();

    boolean addTransaction(Transactions transactions);

    boolean updateTransaction(Transactions transactions);

    boolean deleteTransaction(int transactionId);

    List<Transactions> getTransactionsBySenderId(int senderId);

    List<Transactions> getTransactionsByReceiverId(int receiverId);
}
