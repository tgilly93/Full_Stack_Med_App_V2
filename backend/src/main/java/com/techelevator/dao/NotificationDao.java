package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Notification;

public interface NotificationDao {
    List<Notification> getNotificationsByUserId(int userId);

    Notification getNotificationById(int notificationId);

    boolean createNotification(Notification notification);

    boolean updateNotificationStatus(int notificationId, String notificationStatus);

    boolean deleteNotification(int notificationId);
}
