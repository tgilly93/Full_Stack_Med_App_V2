package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Messages;
import com.techelevator.service.MessagesService;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessagesController {
    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("{messageId}")
    public Messages getMessageById(@PathVariable int messageId) {
        return messagesService.getMessagesById(messageId);
    }

    @GetMapping("/user/{userId}") // Inbox
    public List<Messages> getMessagesForUser(@PathVariable int userId) {
        return messagesService.getMessagesForUser(userId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST', 'ROLE_PATIENT')")
    @PostMapping
    public boolean sendMessage(@RequestBody Messages message) {
        return messagesService.sendMessage(message);
    }

    @DeleteMapping("/user-delete/{messageId}/user/{userId}")
    public boolean softDeleteMessage(@PathVariable int messageId, @PathVariable int userId) {
        return messagesService.softDeleteMessage(messageId, userId);
    }

    @DeleteMapping("/admin-delete/{messageId}")
    public boolean hardDeleteMessage(@PathVariable int messageId) {
        return messagesService.hardDeleteMessage(messageId);
    }
}
