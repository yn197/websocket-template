package com.example.websocket.controller;

import com.example.websocket.async.AsyncMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * yi
 * @author nisang
 * 2023/12/31 18:38
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Resource
    private ExecutorService executorService;
    @Resource
    private AsyncMethod asyncMethod;

    @GetMapping("/helloThread")
    public void helloThread(){
        executorService.execute(() -> {
            for (int i = 0;i < 100;i++){
                System.out.println("111");
            }
        });
        executorService.execute(() -> {
            for (int i = 0;i < 100;i++){
                System.out.println("222");
            }
        });
    }

    @GetMapping("/helloAsync")
    public String helloAsync(){
        // 这个方法是异步的
        asyncMethod.print();
        System.out.println("print方法还在循环，但我已经可以执行了");
        return "print方法还在循环，但我已经可以执行了";
    }

}
