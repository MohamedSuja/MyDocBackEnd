package com.suja.mydoc.repositories;

import com.suja.mydoc.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
