package com.example.websocket.client;

import com.example.websocket.hander.ClientWebSocketHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author nisang
 * 2023/12/31 14:27
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Service
public class MyWebSocketClient {

    @Resource
    private ClientWebSocketHandler clientWebSocketHandler;

    public void sendMessage(String message) {
        try {
            // 发送消息给服务器
            clientWebSocketHandler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}