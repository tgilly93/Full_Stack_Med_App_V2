package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST')") 
    @GetMapping
    public List<Transactions> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')") 
    @GetMapping("/{transactionId}")
    public Transactions getTransactionById(@PathVariable int transactionId) {
        return transactionsService.getTransactionsById(transactionId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_PATIENT')")
    @PostMapping
    public boolean addTransaction(@RequestBody Transactions transactions) {
        return transactionsService.addTransaction(transactions);
    }

    @PreAuthorize("hasRole ('ROLE_ADMIN')")
    @PutMapping("/{transactionId}")
    public boolean updateTransaction(@PathVariable int transactionId, @RequestBody Transactions transactions) {
        transactions.setTransactionId(transactionId);
        return transactionsService.updateTransaction(transactions);
    }

    @PreAuthorize("hasRole ('ROLE_ADMIN')")
    @DeleteMapping("/{transactionId}")
    public boolean deleteTransaction(@PathVariable int transactionId) {
        return transactionsService.deleteTransaction(transactionId);
    }

    @PreAuthorize("@securityService.isUserIdMatching(#senderId, authentication.principal.userId) or hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/sender/{senderId}")
    public List<Transactions> getTransactionsBySenderId(@PathVariable int senderId) {
        return transactionsService.getTransactionsBySenderId(senderId);
    }

    @PreAuthorize("@securityService.isUserIdMatching(#receiverId, authentication.principal.userId) or hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/receiver/{receiverId}")
    public List<Transactions> getTransactionsByReceiverId(@PathVariable int receiverId) {
        return transactionsService.getTransactionByReceiverId(receiverId);
    }

}
