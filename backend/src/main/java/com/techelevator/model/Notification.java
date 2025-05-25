package com.techelevator.model;

import java.time.LocalDateTime;

public class Notification {
    private int notificationId;
    private int userId;
    private LocalDateTime dateTime;
    private String notificationContent;
    private String notificationStatus;

    public Notification() {

    }

    public Notification(int notificationId, int userId, LocalDateTime dateTime, String notificationContent, String notificationStatus) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.notificationContent = notificationContent;
        this.notificationStatus = notificationStatus;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                ", notificationContent='" + notificationContent + '\'' +
                ", notificationStatus='" + notificationStatus + '\'' +
                '}';
    }
}
