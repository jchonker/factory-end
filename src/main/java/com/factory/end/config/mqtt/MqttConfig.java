package com.factory.end.config.mqtt;

import com.factory.end.mqtt.MqttPushClient;
import com.factory.end.task.TimerTask;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author jchonker
 * @Date 2020/9/4 14:42
 * @Version 1.0
 */
@Component
@ConfigurationProperties("mqtt")
@Setter
@Getter
public class MqttConfig {
    @Autowired
    private MqttPushClient mqttPushClient;

    @Autowired
    private TimerTask timerTask;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接地址
     */
    private String hostUrl;

    /**
     * 客户Id
     */
    private String clientID;

    /**
     * 默认连接话题
     */
    private String defaultTopic;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 保持连接数
     */
    private int keepalive;

    @Bean
    public MqttPushClient getMqttPushClient() {
//        System.out.println("hostUrl: "+ hostUrl);
//        System.out.println("clientID: "+ clientID);
//        System.out.println("username: "+ username);
//        System.out.println("password: "+ password);
//        System.out.println("timeout: "+timeout);
//        System.out.println("keepalive: "+ keepalive);
//        mqttPushClient.connect(hostUrl, clientID, username, password, timeout, keepalive);
//        // 以/#结尾表示订阅所有以test开头的主题
//        mqttPushClient.subscribe(defaultTopic, 0);

        //订阅成功，连接成功后调用执行任务
        //timerTask.startTask();

        return mqttPushClient;
    }
}
