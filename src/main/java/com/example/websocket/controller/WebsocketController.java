package com.example.websocket.controller;

import com.example.websocket.server.WebSocket;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author nisang
 * 2023/12/31 14:43
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@RestController
@RequestMapping
public class WebsocketController {

    /**
     * 服务端推送信息到客户端
     */
    @RequestMapping(value = "/")
    public void pushMsgToClient() {
        WebSocket.sendAllMessage("Hello WebSocket");
    }
}
