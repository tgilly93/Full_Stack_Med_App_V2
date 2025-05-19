package com.techelevator.model;

public class Transactions {
    private int transactionId;

    private int senderId;

    private int receiverId;

    public Transactions() {

    }

    public Transactions(int transactionId, int senderId, int receiverId) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                '}';
    }
}
