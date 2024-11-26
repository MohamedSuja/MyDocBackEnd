package com.suja.mydoc.service;

import com.suja.mydoc.constant.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service
public class LocationService {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public boolean updateUserLocation(String location) {
        kafkaTemplate.send(AppConstant.USER_LOCATION, location);
        return true;
    }


}
