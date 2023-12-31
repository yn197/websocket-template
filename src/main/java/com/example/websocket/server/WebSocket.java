package com.example.websocket.server;

import com.alibaba.fastjson.JSONObject;
import com.example.websocket.constant.WebsocketConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author nisang
 * 2023/12/31 11:23
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {

    private Session session;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WebSocket)) {
            return false;
        }
        WebSocket webSocket = (WebSocket) o;
        return Objects.equals(session, webSocket.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }

    private static final CopyOnWriteArraySet<WebSocket> WEB_SOCKETS = new CopyOnWriteArraySet<>();


    /**
     * websocket是客户端和服务端之间建立了一个连接，建立完连接以后，会生成一个websocket对象，我们可以用这个对象来执行发送，接收等操作。但是这只是一个存在于客户端与服务器之间的链接，换句话说，系统只能识别到这个websocket连接是对应于哪个页面（浏览器），而这个页面在系统中是对应哪个用户（数据库中的用户，或者根本就没有对应任何用户，即未登录，只是一个游客），我们是无法从这个websocket对象中获取的。
     * 所以我们需要创建一个Map对象，用于将websocket对象和实际的user对象进行关联，这样为我们后续向特定的用户推送消息做铺垫
     */
    private static final Map<String, Session> SESSION_POOL = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        try {
            this.session = session;
            WEB_SOCKETS.add(this);
            SESSION_POOL.put(userId, session);
            log.info("【websocket消息】有新的连接，总数为: {}", WEB_SOCKETS.size());
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose(@PathParam(value = "userId") String userId) {
        try {
            WEB_SOCKETS.remove(this);
            SESSION_POOL.remove(userId);
            log.info("【websocket消息】连接断开，总数为: {}", WEB_SOCKETS.size());
        } catch (Exception e) {
        }
    }

    @OnMessage
    public void onMessage(String message) {
        log.debug("【websocket消息】收到客户端消息: {}", message);
        JSONObject obj = new JSONObject();
        //业务类型,是模拟查询出来的业务数据
        obj.put(WebsocketConst.MSG_OBJ, WebsocketConst.MSG_USERNAME);
        //消息内容
        obj.put(WebsocketConst.MSG_TYPE, WebsocketConst.MSG_PONG);
        session.getAsyncRemote().sendText(obj.toJSONString());
    }


    @OnError
    public void onError(Session session, @PathParam(value = "userId") String userId, Throwable t) {
        try {
            if (session.isOpen()) {
                session.close();
            }
            WEB_SOCKETS.remove(this);
            SESSION_POOL.remove(userId);
            log.info("【websocket消息】连接[错误]断开，总数为: {}, 错误：{}", WEB_SOCKETS.size(), t.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此为广播消息
     *
     * @param message 发送的消息
     */
    public static void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息:" + message);
        for (WebSocket webSocket : WEB_SOCKETS) {
            try {

                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此为单人消息
     *
     * @param userId  用户id
     * @param message 发送的消息
     */
    public static void sendOneMessage(String userId, String message) {
        Session session = SESSION_POOL.get(userId);
        if (session != null && session.isOpen()) {
            try {
                log.info("【websocket消息】 单点消息:" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此为单点消息(多人)
     *
     * @param userIds 用户ids
     * @param message 发送的消息
     */
    public static void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = SESSION_POOL.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    log.info("【websocket消息】 单点消息:" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}