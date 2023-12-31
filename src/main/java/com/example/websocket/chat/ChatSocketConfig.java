package com.example.websocket.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 聊天室 聊天室  访问地址localhost:9091/chat.html
 * @author nisang
 * 2023/12/31 19:31
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Configuration
@EnableWebSocket
public class ChatSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebSocketHandler(), "/chat").setAllowedOrigins("*");
    }
}