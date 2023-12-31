package com.example.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketConnectionManager;

import javax.annotation.Resource;

/**
 * 调度Scheduling
 *
 * @author nisang
 * 2023/12/31 14:09
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Component
@Slf4j
public class WebsocketScheduleConfig {

    @Resource
    private WebSocketConnectionManager connectionManager;

    @Scheduled(fixedRate = 1000)
    public void reconnectTask() {
        log.info("心跳检查");
        if (!connectionManager.isConnected()) {
            log.info("重新连接.....");
            connectionManager.stop();
            connectionManager.start();
        }
    }
}
