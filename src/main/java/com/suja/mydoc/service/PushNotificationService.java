package com.suja.mydoc.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.suja.mydoc.dto.PushNotificationRequestDto;
import com.suja.mydoc.dto.PushNotificationResponseDto;
import com.suja.mydoc.exception.ResourceNotFoundException;
import com.suja.mydoc.model.DeviceToken;
import com.suja.mydoc.repositories.DeviceTokenRepository;
import com.suja.mydoc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class PushNotificationService {


    private final DeviceTokenRepository deviceTokenRepository;
    private final UserRepository userRepository;

    public PushNotificationResponseDto sendPushNotificationToToken(
            Integer userId,
            PushNotificationRequestDto requestDto) {


        List<String> tokens = deviceTokenRepository.findAllByUser(
                        userRepository.findById(userId)
                                .orElseThrow(() -> new RuntimeException("User not found"))
                ).stream()
                .map(DeviceToken::getDevice_token)
                .toList();


        List<CompletableFuture<Void>> futures = tokens.stream()
                .map(token -> CompletableFuture.runAsync(() -> {
                    Message message = Message.builder()
                            .setToken(token)
                            .setNotification(Notification.builder()
                                    .setTitle(requestDto.getTitle())
                                    .setBody(requestDto.getMessage())
                                    .build())
                            .build();
                    try {
                        String response = FirebaseMessaging.getInstance().send(message);
                        log.info("Sent message to token {}: {}", token, response);
                    } catch (Exception e) {
                        log.error("Error sending message to token {}: {}", token, e.getMessage());
                    }
                }))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return PushNotificationResponseDto.builder()
                .status(HttpStatus.OK.value())
                .message("Notification sent successfully")
                .build();

    }


    public PushNotificationResponseDto sendPushNotificationToTopic(PushNotificationRequestDto requestDto) {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(requestDto.getTitle())
                        .setBody(requestDto.getMessage())
                        .build())
                .setTopic(requestDto.getTopic())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Sent message: " + response);
            return PushNotificationResponseDto.builder()
                    .status(HttpStatus.OK.value())
                    .message("Notification sent successfully")
                    .build();
        } catch (Exception e) {
            log.error("Error sending message: " + e.getMessage());
            throw new ResourceNotFoundException("Error sending notification");
        }
    }

}