package com.suja.mydoc.dto;

import com.suja.mydoc.role.NotRoles;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDto implements Serializable {

    private boolean status = true;
    private int errorCode;
    private String errorDescription;
    private Object responseDto;


}
