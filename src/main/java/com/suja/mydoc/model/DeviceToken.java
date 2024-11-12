package com.suja.mydoc.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device_token")
public class DeviceToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false, unique = true, length = 255)
    private String device_token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private DeviceType device_type;

    @Column(nullable = false)
    private Boolean is_active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        if (is_active == null) {
            is_active = true; // Default to true if not set
        }
        if (created_at == null) {
            created_at = LocalDateTime.now();
        }
        updated_at = LocalDateTime.now(); // Set updated_at on initial save
    }

    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now(); // Update timestamp on update
    }

    public enum DeviceType {
        IOS, ANDROID, WEB
    }
}
