package com.factory.end.cron;

import com.factory.end.util.PushDataToClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author jchonker
 * @Date 2020/8/21 0:41
 * @Version 1.0
 * 推送数据到客户端的定时任务
 */
@Component
public class PushDataScheduled {

//    @Scheduled(cron = "0/10 * * * * ? ")
//    public void testCron(){
//        System.out.println("定时任务执行....");
//    }

    @Autowired
    PushDataToClient pushDataToClient;

    private String clientUserId = "10";

    /**
     * 推送数据到websocket客户端
     */
//    @Scheduled(cron = "0/3 * * * * ? ")
//    public void pushData(){
//        pushDataToClient.push(clientUserId);
//    }

}
