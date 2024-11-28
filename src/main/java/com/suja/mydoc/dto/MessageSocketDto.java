package com.suja.mydoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageSocketDto {
    private String senderId;
    private String senderName;
    private String targetUserId;
    private String targetUserName;
    private String message;

}
