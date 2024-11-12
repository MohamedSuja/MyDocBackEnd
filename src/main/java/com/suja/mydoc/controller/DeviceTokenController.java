package com.suja.mydoc.controller;


import com.suja.mydoc.dto.DeviceTokenRequestDto;
import com.suja.mydoc.service.DeviceTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/device-token")
@RequiredArgsConstructor
@Validated
public class DeviceTokenController {


    private final DeviceTokenService service;

    @PostMapping("/save")
    public ResponseEntity<Void> saveDeviceToken(
            @RequestBody DeviceTokenRequestDto request
    ) {
        return ResponseEntity.ok(service.saveDeviceToken(request));
    }
}
