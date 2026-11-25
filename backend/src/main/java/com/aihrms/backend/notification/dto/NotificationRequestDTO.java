package com.aihrms.backend.notification.dto;

import com.aihrms.backend.notification.enums.NotificationType;

public class NotificationRequestDTO {

    private String title;

    private String message;

    private NotificationType type;

    public NotificationRequestDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}