package com.example.websocket.hander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;

/**
 * @author nisang
 * 2023/12/31 12:03
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class ClientWebSocketHandler implements WebSocketHandler {
    public static final Logger logger = LoggerFactory.getLogger(ClientWebSocketHandler.class);
    private WebSocketSession webSocketSession;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 在连接建立时，获取 WebSocketSession 对象
        this.webSocketSession=session;
        logger.info("客户端连接成功:{}", session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("客户端接受到消息:{}", message.getPayload());
        Object payload = message.getPayload();
        if (payload instanceof String) {
            String serverMessage = (String) payload;
            logger.info("客户端接受到消息: {}", serverMessage);

            // 处理来自服务器的字符串消息
        } else {
            // 如果需要，处理其他类型的消息
            logger.warn("接收到不支持的消息类型");
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("客户端发送错误:{}", exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("客户端关闭连接:{}", closeStatus.getCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 提供一个方法供外部调用发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        if (webSocketSession != null && webSocketSession.isOpen()) {
            webSocketSession.sendMessage(new TextMessage(message));
        }
    }
}
