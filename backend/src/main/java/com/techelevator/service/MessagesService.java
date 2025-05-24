package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.MessagesDao;
import com.techelevator.model.Messages;

@Service
public class MessagesService {
    private final MessagesDao messagesDao;

    public MessagesService(MessagesDao messagesDao) {
        this.messagesDao = messagesDao;
    }

    public Messages getMessagesById(int messageId) {
        return messagesDao.getMessageById(messageId);
    }

    public List<Messages> getMessagesForUser(int userId) {
        return messagesDao.getMessagesForUser(userId);
    }

    public boolean sendMessage(Messages message) {
        return messagesDao.sendMessage(message);
    }

    public boolean softDeleteMessage(int messageId, int userId) {
        return messagesDao.softDeleteMessage(messageId, userId);
    }

    public boolean hardDeleteMessage(int messageId) {
        return messagesDao.hardDeleteMessage(messageId);
    }
}
