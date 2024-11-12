package com.suja.mydoc.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PushNotificationResponseDto {

    private int status;
    private String message;
}
