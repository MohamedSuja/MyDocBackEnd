package com.suja.mydoc.config;

import javax.annotation.PreDestroy;


import com.corundumstudio.socketio.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SocketIOConfig {
    // I have set the configuration values in application.yaml file
    @Value("${socket.host}")
    private String socketHost;
    @Value("${socket.port}")
    private int socketPort;

    // SocketIOServer class is used to create a socket server
    private SocketIOServer server;


    @Bean
    public SocketIOServer socketIOServer() {
        // Configuration object holds the server settings
        Configuration config = new Configuration();

        config.setHostname(socketHost);
        config.setPort(socketPort);

        server = new SocketIOServer(config);
        server.start();

        server.addConnectListener(client -> log.info("Client connected: {}", client.getSessionId()));
        server.addDisconnectListener(client -> log.info("Client disconnected: {}", client.getSessionId()));

        return server;
    }

    @PreDestroy
    public void stopSocketServer() {
        this.server.stop();
    }
}
