    package com.aihrms.backend.notification.entity;

    import com.aihrms.backend.notification.enums.NotificationType;
    import jakarta.persistence.*;

    import java.time.LocalDateTime;

    @Entity
    @Table(name = "notifications")
    public class Notification {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String title;

        @Column(length = 1000)
        private String message;

        @Enumerated(EnumType.STRING)
        private NotificationType type;

        @Column(nullable = false)
        private Boolean isRead = false;

        private LocalDateTime createdAt;

        public Notification() {
        }

        @PrePersist
        public void prePersist() {
            createdAt = LocalDateTime.now();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public Boolean getIsRead() {
            return isRead;
        }

        public void setIsRead(Boolean read) {
            isRead = read;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }