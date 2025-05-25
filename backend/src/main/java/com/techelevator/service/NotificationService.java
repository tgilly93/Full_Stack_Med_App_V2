package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.NotificationDao;
import com.techelevator.model.Notification;

@Service
public class NotificationService {
    private final NotificationDao notificationDao;

    public NotificationService(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationDao.getNotificationsByUserId(userId);
    }

    public Notification getNotificationById(int notificationId) {
        return notificationDao.getNotificationById(notificationId);
    }

    public boolean createNotification(Notification notification) {
        return notificationDao.createNotification(notification);
    }

    public boolean updateNotificationStatus(int notificationId, String notificationStatus) {
        return notificationDao.updateNotificationStatus(notificationId, notificationStatus);
    }

    public boolean deleteNotification(int notificationId) {
        return notificationDao.deleteNotification(notificationId);
    }
}
