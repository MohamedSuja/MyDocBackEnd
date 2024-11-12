package com.suja.mydoc.repositories;

import com.suja.mydoc.model.DeviceToken;
import com.suja.mydoc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {


    List<DeviceToken> findAllByUser(User user);


}