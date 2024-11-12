package com.suja.mydoc.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PushNotification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private Boolean isRead = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime sentAt = LocalDateTime.now();

    private LocalDateTime readAt;

    @PreUpdate
    public void preUpdate() {
        if (isRead && readAt == null) {
            readAt = LocalDateTime.now();
        }
    }
}

