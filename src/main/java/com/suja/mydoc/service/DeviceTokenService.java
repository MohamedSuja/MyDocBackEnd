package com.suja.mydoc.service;


import com.suja.mydoc.dto.DeviceTokenRequestDto;
import com.suja.mydoc.model.DeviceToken;
import com.suja.mydoc.repositories.DeviceTokenRepository;
import com.suja.mydoc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceTokenService {

    private final UserRepository userRepository;
    private final DeviceTokenRepository deviceTokenRepository;


    public Void saveDeviceToken(DeviceTokenRequestDto request) {

        log.info("Saving device token for user {}", request.getUserId());

        var response = DeviceToken.builder()
                .user(
                        userRepository.findById(request.getUserId())
                                .orElseThrow(() -> new RuntimeException("User not found"))
                )
                .device_token(request.getDevice_token())
                .device_type(request.getDevice_type())
                .created_at(LocalDateTime.now())
                .is_active(true)
                .build();

         deviceTokenRepository.save(response);

        return null;


    }
}
