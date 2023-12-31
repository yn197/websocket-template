package com.example.websocket.init;

import com.example.websocket.server.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author nisang
 * 2023/12/31 13:55
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Component
public class WebSocketInitializer {

    private ScheduledExecutorService scheduler;

    @Autowired
    public WebSocketInitializer(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }
    @PostConstruct
    public void init() {
        scheduler.schedule(() -> WebSocket.sendAllMessage("Welcome to the websocket server"), 5, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void cleanup() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}
