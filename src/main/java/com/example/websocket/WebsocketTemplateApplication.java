package com.example.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.client.WebSocketConnectionManager;

import javax.annotation.Resource;

/**
 * @author 16074
 */
@EnableScheduling
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WebsocketTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketTemplateApplication.class, args);
    }

}
