package com.suja.mydoc.dto;


import com.suja.mydoc.model.DeviceToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceTokenRequestDto {

    private Integer userId;
    private String device_token;
    private DeviceToken.DeviceType device_type;

}
