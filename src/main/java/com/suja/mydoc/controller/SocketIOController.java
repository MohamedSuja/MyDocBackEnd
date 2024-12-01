package com.suja.mydoc.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.suja.mydoc.dto.MessageSocketDto;
import com.suja.mydoc.dto.PushNotificationRequestDto;
import com.suja.mydoc.service.PushNotificationService;
import com.suja.mydoc.service.SocketIOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class SocketIOController {
    protected final SocketIOServer socketServer;

    @Autowired
    private SocketIOService shocketIOService;

    public SocketIOController(SocketIOServer socketServer) {
        this.socketServer = socketServer;
        this.socketServer.addEventListener("messageEvent", MessageSocketDto.class, messageEvent);
    }

    public DataListener<MessageSocketDto> messageEvent = new DataListener<>() {
        @Override
        public void onData(SocketIOClient client, MessageSocketDto messageSocketDto, AckRequest ackRequest) {
            log.info("Demo event received: {}", messageSocketDto);


            // Add the time to the response
            Map<String, Object> sendMessage = new HashMap<>();
            sendMessage.put("senderId", messageSocketDto.getSenderId());
            sendMessage.put("senderName", messageSocketDto.getSenderName());
            sendMessage.put("targetUserId", messageSocketDto.getTargetUserId());
            sendMessage.put("targetUserName", messageSocketDto.getTargetUserName());
            sendMessage.put("message", messageSocketDto.getMessage());
            sendMessage.put("time", LocalDateTime.now().toString());


            socketServer.getBroadcastOperations().sendEvent(messageSocketDto.getTargetUserId(),client, sendMessage);
            // Add your business logic here.
            ackRequest.sendAckData(
                    "Message send to target user successfully"
            );


            PushNotificationRequestDto  pushNotification = PushNotificationRequestDto.builder()
                    .title("New Message")
                    .message(messageSocketDto.getMessage())
                    .topic("all")
                    .build();



            shocketIOService.sendNotificationToMessage(Integer.parseInt(messageSocketDto.getTargetUserId()),pushNotification);

        }
    };
}