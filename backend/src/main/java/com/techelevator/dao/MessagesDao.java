package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Messages;

public interface MessagesDao {
    Messages getMessageById(int messageId);

    List<Messages> getMessagesForUser(int userId);

    boolean sendMessage(Messages message);

    boolean softDeleteMessage(int messageId, int userId);

    boolean hardDeleteMessage(int messageId);
}
