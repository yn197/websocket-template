package com.example.websocket.chat;

/**
 * @author nisang
 * 2023/12/31 19:45
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class ChatMessage {
    private String sender;
    private String content;

    public ChatMessage() {
    }

    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
