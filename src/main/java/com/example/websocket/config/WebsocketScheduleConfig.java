package com.example.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class WebsocketScheduleConfig {
    public static final Logger logger = LoggerFactory.getLogger(WebsocketScheduleConfig.class);


    @Scheduled(fixedRate = 1000)
    public void reconnectTask() {
        WebSocketConnectionManager connectionManager = WebSocketClientConfig.CONNECTION_MANAGER;
        logger.info("心跳检查");
        if (!connectionManager.isConnected()) {
            logger.info("重新连接.....");
            connectionManager.stop();
            connectionManager.start();
        }
    }

}
