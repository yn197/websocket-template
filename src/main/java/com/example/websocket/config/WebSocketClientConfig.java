package com.example.websocket.config;

import com.example.websocket.hander.ClientWebSocketHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.util.UUID;

/**
 * @author nisang
 * 2023/12/31 12:04
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Configuration
public class WebSocketClientConfig {
    @Value("${websocket.server.host}")
    private String webSocketServerHost;

    @Value("${websocket.server.port}")
    private int webSocketServerPort;

    @Value("${websocket.server.path}")
    private String webSocketServerPath;
    @Bean
    public WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public ClientWebSocketHandler clientWebSocketHandler() {
        return new ClientWebSocketHandler();
    }

    @Bean
    public WebSocketConnectionManager connectionManager() {
        String webSocketUrl = String.format("ws://%s:%d%s/%s", webSocketServerHost, webSocketServerPort, webSocketServerPath, UUID.randomUUID());
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(
                webSocketClient(),
                clientWebSocketHandler(),
                webSocketUrl
        );
        connectionManager.setAutoStartup(true);
        return connectionManager;
    }
}
