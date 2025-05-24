package com.techelevator.model;

import java.time.LocalDateTime;

public class Messages {
    private int messageId;
    private int senderId;
    private int receiverId;
    private String messageContent;
    private LocalDateTime dateTime;
    private String messageType;
    private boolean senderDeleted;
    private boolean receiverDeleted;

    public Messages() {

    }

    public Messages(int messageId, int senderId, int receiverId, String messageContent, LocalDateTime dateTime,
            String messageType, boolean senderDeleted, boolean receiverDeleted) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageContent = messageContent;
        this.dateTime = dateTime;
        this.messageType = messageType;
        this.senderDeleted = senderDeleted;
        this.receiverDeleted = receiverDeleted;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean isSenderDeleted() {
        return senderDeleted;
    }

    public void setSenderDeleted(boolean senderDeleted) {
        this.senderDeleted = senderDeleted;
    }

    public boolean isReceiverDeleted() {
        return receiverDeleted;
    }

    public void setReceiverDeleted(boolean receiverDeleted) {
        this.receiverDeleted = receiverDeleted;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "messageId=" + messageId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", messageContent='" + messageContent + '\'' +
                ", dateTime=" + dateTime +
                ", messageType='" + messageType + '\'' +
                ", senderDeleted=" + senderDeleted +
                ", receiverDeleted=" + receiverDeleted +
                '}';
    }
}
