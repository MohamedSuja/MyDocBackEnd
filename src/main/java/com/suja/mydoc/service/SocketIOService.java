package com.suja.mydoc.service;

import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.suja.mydoc.dto.PushNotificationRequestDto;
import com.suja.mydoc.dto.PushNotificationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class SocketIOService {


    private final PushNotificationService pushNotificationService;


    public void sendNotificationToMessage(Integer userId,@RequestBody PushNotificationRequestDto requestDto) {


        log.info("Sending message to user: {}", userId, requestDto);
        pushNotificationService.sendPushNotificationToToken(userId, requestDto);



//        Message message = Message.builder()
//                .setNotification(Notification.builder()
//                        .setTitle(requestDto.getTitle())
//                        .setBody(requestDto.getMessage())
//                        .build())
//                .setTopic(requestDto.getTopic())
//                .build();
//        try {
//            String response = FirebaseMessaging.getInstance().send(message);
//            log.info("Sent message to topic {}: {}", requestDto.getTopic(), response);
//        } catch (Exception e) {
//            log.error("Error sending message to topic {}: {}", requestDto.getTopic(), e.getMessage());
//        }

    }


}
