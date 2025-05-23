package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Transactions;
import com.techelevator.service.TransactionsService;

@RestController
@CrossOrigin
@RequestMapping("/api/transactions")
public class TransactionsController {
    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping
    public List<Transactions> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    @GetMapping("/{transactionId}")
    public Transactions getTransactionById(@PathVariable int transactionId) {
        return transactionsService.getTransactionsById(transactionId);
    }

    @PostMapping
    public boolean addTransaction(@RequestBody Transactions transactions) {
        return transactionsService.addTransaction(transactions);
    }

    @PutMapping("/{transactionId}")
    public boolean updateTransaction(@PathVariable int transactionId, @RequestBody Transactions transactions) {
        transactions.setTransactionId(transactionId);
        return transactionsService.updateTransaction(transactions);
    }

    @DeleteMapping("/{transactionId}")
    public boolean deleteTransaction(@PathVariable int transactionId) {
        return transactionsService.deleteTransaction(transactionId);
    }

    @GetMapping("/sender/{senderId}")
    public List<Transactions> getTransactionsBySenderId(@PathVariable int senderId) {
        return transactionsService.getTransactionsBySenderId(senderId);
    }

    @GetMapping("/receiver/{receiverId}")
    public List<Transactions> getTransactionsByReceiverId(@PathVariable int receiverId) {
        return transactionsService.getTransactionByReceiverId(receiverId);
    }

}
