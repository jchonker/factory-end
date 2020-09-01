package com.factory.end.util;

import com.alibaba.fastjson.JSON;
import com.factory.end.dto.second.MyEquipmentDto;
import com.factory.end.service.second.MyEquipmentService;
import com.factory.end.util.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/14 11:16
 * @Version 1.0
 * 推送数据库信息给websocket客户端
 */
@Component
public class PushDataToClient {
    @Autowired
    private MyEquipmentService myEquipmentService;

    /**
     * 推送
     */
    public void push(String userId){
        //获取信息
        List<MyEquipmentDto> myEquipmentDtoList = myEquipmentService.findAll();
        //将信息对象转换成json字符串
        String jsonString = JSON.toJSONString(myEquipmentDtoList);
        //连接成功推送
        try {
            WebSocketServer.sendInfo(jsonString,userId);
        } catch (IOException e) {
            System.out.println("消息推送失败!");
        }
    }
}
