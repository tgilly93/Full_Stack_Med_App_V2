package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Notification;
import com.techelevator.service.NotificationService;

@RestController
@CrossOrigin
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable int userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @GetMapping("/{notificationId}")
    public Notification getNotificationById(@PathVariable int notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @PutMapping("/{notificationId}")
    public boolean updateNotificationStatus(@PathVariable int notificationId, @RequestParam String notificationStatus) {
        return notificationService.updateNotificationStatus(notificationId, notificationStatus);
    }

    @DeleteMapping("/{notificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteNotification(@PathVariable int notificationId) {
        return notificationService.deleteNotification(notificationId);
    }
}
