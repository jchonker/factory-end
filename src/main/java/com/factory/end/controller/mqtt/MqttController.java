package com.factory.end.controller.mqtt;

import com.alibaba.fastjson.JSON;
import com.factory.end.model.primary.Scheduling;
import com.factory.end.mqtt.MqttPushClient;
import com.factory.end.service.primary.SchedulingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jchonker
 * @Date 2020/9/4 15:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/mqtt")
@Api(description = "测试mqtt")
public class MqttController {
    Logger logger = LoggerFactory.getLogger(MqttController.class);

    @Autowired
    MqttPushClient mqttPushClient;

    @Value("${mqtt.target-topic}")
    private String target_topic;

    @Autowired
    private SchedulingService schedulingService;

    /**
     * 连接mqtt服务器
     */
    @GetMapping("/connect")
    @ApiOperation("连接mqtt服务器")
    public String connect(){
        String host = "127.0.0.1:61613";
        String clientID = "jcClient";
        String username = "admin";
        String password = "password";
        Integer timeout = 1000;
        Integer keepalive = 1000;

        logger.info("开始连接mqtt服务器...");
        mqttPushClient.connect(host,clientID,username,password,timeout,keepalive);
        return "连接成功";
    }

    /**
     * 发布
     */
    @GetMapping("/publish")
    @ApiOperation("发布消息")
    public String publish(){
        int qos = 0;
        boolean retained = true;
        String topic = "testTopic";
        String pushMessage = "this is my testPushMessage!";

        logger.info("开始发布消息...");
        mqttPushClient.publish(qos,retained,topic,pushMessage);
        return "发布成功";
    }

    /**
     * 订阅主题
     */
    @GetMapping("/subscribe")
    @ApiOperation("订阅主题")
    public String subscribe(){
        String topic = "testTopic";
        int qos = 0;
        mqttPushClient.subscribe(topic,qos);
        return "订阅成功";
    }


    /**
     * 使用默认主题,发送默认信息
     * @return
     */
    @GetMapping(value = "/publishTopic")
    @ApiOperation("使用默认主题,发送默认信息")
    public String publishTopic() {
        String topicString = target_topic;
        String orderNo = "O1599460909306";
        Scheduling scheduling = schedulingService.findByOrderNo(orderNo);
        if(scheduling != null){
            String jsonString = JSON.toJSONString(scheduling);
            mqttPushClient.publish(0, false, topicString, jsonString);
            return "ok";
        }
        else {
            logger.info("查询无值");
            return "error";
        }
    }

    /**
     * 发送自定义消息内容（使用默认主题）
     * @param data
     * @return
     */
    @GetMapping("/publishTopic/{data}")
    @ApiOperation("发送自定义消息内容（使用默认主题）")
    public String test1(@PathVariable("data") String data) {
        String topicString = target_topic;
        mqttPushClient.publish(0,false,topicString, data);
        return "ok";
    }

    /**
     * 发送自定义消息内容，且指定主题
     * @param topic
     * @param data
     * @return
     */
    @GetMapping("/publishTopic/{topic}/{data}")
    @ApiOperation("发送自定义消息内容，且指定主题")
    public String test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
        mqttPushClient.publish(0,false,topic, data);
        return "ok";
    }

}
