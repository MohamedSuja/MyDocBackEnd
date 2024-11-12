package com.suja.mydoc.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PushNotificationRequestDto {

    private String title;
    private String message;
    private String topic;
    private String notificationsToken;
    private Integer userId;

}
