package com.aihrms.backend.notification.mapper;

import com.aihrms.backend.notification.dto.NotificationRequestDTO;
import com.aihrms.backend.notification.dto.NotificationResponseDTO;
import com.aihrms.backend.notification.entity.Notification;

public class NotificationMapper {

    private NotificationMapper() {
    }

    public static Notification toEntity(NotificationRequestDTO dto) {

        Notification notification = new Notification();

        notification.setTitle(dto.getTitle());
        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());

        return notification;
    }

    public static NotificationResponseDTO toDTO(Notification notification) {

        NotificationResponseDTO dto = new NotificationResponseDTO();

        dto.setId(notification.getId());
        dto.setTitle(notification.getTitle());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setIsRead(notification.getIsRead());
        dto.setCreatedAt(notification.getCreatedAt());

        return dto;
    }
}