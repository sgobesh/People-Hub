package com.aihrms.backend.notification.controller;

import com.aihrms.backend.notification.dto.NotificationRequestDTO;
import com.aihrms.backend.notification.dto.NotificationResponseDTO;
import com.aihrms.backend.notification.service.NotificationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Create Notification
    @PostMapping
    public ResponseEntity<NotificationResponseDTO> createNotification(
            @RequestBody NotificationRequestDTO dto) {

        return ResponseEntity.ok(
                notificationService.createNotification(dto));
    }

    // Get All Notifications
    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getAllNotifications() {

        return ResponseEntity.ok(
                notificationService.getAllNotifications());
    }

    // Get Unread Count
    @GetMapping("/unread-count")
    public ResponseEntity<Long> getUnreadCount() {

        return ResponseEntity.ok(
                notificationService.getUnreadCount());
    }
@GetMapping("/unread")
public ResponseEntity<Long> getUnread() {
    return ResponseEntity.ok(notificationService.getUnreadCount());
}
    // Mark Notification as Read
    @PutMapping("/read/{id}")
    public ResponseEntity<String> markAsRead(
            @PathVariable Long id) {

        notificationService.markAsRead(id);

        return ResponseEntity.ok("Notification marked as read");
    }

    // Mark All Notifications as Read
    @PutMapping("/read-all")
    public ResponseEntity<String> markAllAsRead() {

        notificationService.markAllAsRead();

        return ResponseEntity.ok("All notifications marked as read");
    }

    // Delete Notification
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return ResponseEntity.ok("Notification deleted successfully");
    }
}