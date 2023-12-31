package com.example.websocket.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author nisang
 * 2023/12/31 19:45
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) {
        return new ChatMessage(message.getSender(), message.getContent());
    }
}
