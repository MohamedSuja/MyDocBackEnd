package com.suja.mydoc.controller;


import com.suja.mydoc.dto.PushNotificationRequestDto;
import com.suja.mydoc.dto.PushNotificationResponseDto;
import com.suja.mydoc.service.PushNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/notifications")
public class PushNotificationController {

    @Autowired
    private PushNotificationService pushNotificationService;


    @PostMapping("/user")
    public PushNotificationResponseDto sendNotificationToToken(
            @RequestParam("user-id") Integer userId,
            @RequestBody PushNotificationRequestDto requestDto) {
        return  pushNotificationService.sendPushNotificationToToken(
                userId,
                requestDto);
    }

    @PostMapping("/topic")
    public PushNotificationResponseDto sendNotificationToTopic(@RequestBody PushNotificationRequestDto requestDto) {
        log.info("Sending notification to topic: {}", requestDto.getTopic());

        return  pushNotificationService.sendPushNotificationToTopic(requestDto);
    }


}
