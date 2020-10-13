package com.factory.end.mqtt;

import com.factory.end.task.MessageArrivedTask;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author jchonker
 * @Date 2020/9/4 14:58
 * @Version 1.0
 * MQTT回调类
 */
@Component
public class PushCallback implements MqttCallback {
    Logger logger = LoggerFactory.getLogger(PushCallback.class);

    @Autowired
    private MessageArrivedTask messageArrivedTask;

    @Override
    public void connectionLost(Throwable throwable) {
        logger.error("mqtt发生异常:");
        logger.error(throwable.getMessage());
        logger.error(throwable.getStackTrace().toString());
        logger.error(throwable.getLocalizedMessage());
        logger.error(throwable.getCause().toString());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        logger.info("收到信息:");
        logger.info("s:"+s);
        byte[] payload = mqttMessage.getPayload();
        String payLoadString = new String(payload);
        logger.info("payLoadString:"+payLoadString);
        logger.info("id:"+mqttMessage.getId());
        logger.info("qos:"+mqttMessage.getQos());

        messageArrivedTask.process(payLoadString);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("推送消息");
        try {
            byte[] payload = iMqttDeliveryToken.getMessage().getPayload();
            String payLoadString = new String(payload);
            logger.info(payLoadString);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
