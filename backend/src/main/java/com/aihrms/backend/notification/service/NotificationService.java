package com.aihrms.backend.notification.service;

import com.aihrms.backend.exception.ResourceNotFoundException;
import com.aihrms.backend.notification.dto.NotificationRequestDTO;
import com.aihrms.backend.notification.dto.NotificationResponseDTO;
import com.aihrms.backend.notification.entity.Notification;
import com.aihrms.backend.notification.enums.NotificationType;
import com.aihrms.backend.notification.mapper.NotificationMapper;
import com.aihrms.backend.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Create Notification using DTO
    public NotificationResponseDTO createNotification(NotificationRequestDTO dto) {

        Notification notification = NotificationMapper.toEntity(dto);

        Notification saved = notificationRepository.save(notification);

        return NotificationMapper.toDTO(saved);
    }

    // Helper method to create notification directly
    public void createNotification(
            String title,
            String message,
            NotificationType type) {

        Notification notification = new Notification();

        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setIsRead(false);

        notificationRepository.save(notification);
    }

    // Get All Notifications
    public List<NotificationResponseDTO> getAllNotifications() {

        return notificationRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(NotificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get Unread Count
    public long getUnreadCount() {
        return notificationRepository.countByIsReadFalse();
    }

    // Mark One Notification as Read
    public void markAsRead(Long id) {

        Notification notification = notificationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notification not found with id : " + id));

        notification.setIsRead(true);

        notificationRepository.save(notification);
    }

    // Mark All Notifications as Read
    public void markAllAsRead() {

        List<Notification> notifications =
                notificationRepository.findByIsReadFalseOrderByCreatedAtDesc();

        notifications.forEach(notification -> notification.setIsRead(true));

        notificationRepository.saveAll(notifications);
    }

    // Delete Notification
    public void deleteNotification(Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found with id : " + id));

        notificationRepository.delete(notification);
    }
}