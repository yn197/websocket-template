package com.example.websocket.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author nisang
 * 2023/12/31 18:39
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Component
public class AsyncMethod {
    /**
     * 异步方法，如果@Async加在类的上面，则整个类中的方法都是异步的
     */
    @Async
    public void print(){
        for (int i = 0;i < 100;i++){
            System.out.println(i);
        }
    }
}
