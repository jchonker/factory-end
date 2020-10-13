package com.factory.end.task;

import com.alibaba.fastjson.JSON;
import com.factory.end.model.primary.Equipment;
import com.factory.end.model.primary.Product;
import com.factory.end.model.primary.Scheduling;
import com.factory.end.mqtt.MqttPushClient;
import com.factory.end.mqtt.PushCallback;
import com.factory.end.service.primary.EquipmentService;
import com.factory.end.service.primary.OrderService;
import com.factory.end.service.primary.ProductService;
import com.factory.end.service.primary.SchedulingService;
import com.factory.end.util.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * @Author jchonker
 * @Date 2020/9/9 17:49
 * @Version 1.0
 */
@Component
public class TimerTask {
    Logger logger = LoggerFactory.getLogger(TimerTask.class);

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private SchedulingService schedulingService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MqttPushClient mqttPushClient;

    @Value("${mqtt.target-topic}")
    private String targetTopic;


    Timer timer = new Timer();

    public void startTask() {
        timer.schedule(new java.util.TimerTask() {
            public void run() {
                System.out.println("-------设定要执行的任务--------");

                //1:先查询出空闲的设备
                logger.info("1:先查询出空闲的设备");
                List<Equipment> equipmentOfLeisure = equipmentService.findAllOfLeisure();
                if (equipmentOfLeisure != null && equipmentOfLeisure.size() > 0) {
                    for (Equipment equipment : equipmentOfLeisure) {
                        //2:根据设备从schduling表查询出适合的订单记录
                        String equipmentNo = equipment.getEquipmentNo();
                        logger.info("2:根据设备从schduling表查询出适合的订单记录");
                        Scheduling scheduling = schedulingService.findOneByEquipmentNoOrderByManuOrder(equipmentNo);
                        if (scheduling != null) {
                            //3:以此条记录中的orderNo字段查询schduing表中有几条orderNo相同的记录(来自同一个大订单)
                            Integer countByOrderNo = productService.findCountByOrderNo(scheduling.getOrderNo());
                            logger.info("schduling表中orderNo为" + scheduling.getOrderNo() + "的记录有" + countByOrderNo + "条");
                            Integer lastSchedulingOfOrder = 0;
                            if (countByOrderNo <= 0) {
                                logger.error("订单异常状态异常");
                                return;
                            } else if (countByOrderNo == 1) {
                                logger.info("最后一个分订单...");
                                lastSchedulingOfOrder = 1;
                            } else if (countByOrderNo > 1) {
                                logger.info("不是最后一个分订单...");
                                lastSchedulingOfOrder = 0;
                            }
                            logger.info(scheduling.toString());
                            //4:将订单插入到product表中
                            Integer id = scheduling.getId();
                            logger.info("3:将订单插入到product表中");
                            Product product = productService.saveProductBySchdulingId(id, lastSchedulingOfOrder);

                            //5:插入成功后将对应设备的【允许生产】改为【1】
                            logger.info("插入成功.将设备" + equipmentNo + "【允许生产】改为1");
                            equipmentService.updateAllowProduct(equipmentNo, 1);

                            //6:在schduling表中删除此条记录
                            logger.info("删除表中id为" + id + "记录!");
                            schedulingService.deleteById(id);

//                            //5:插入成功后将返回的记录通过request主题发送出去
//                            logger.info("4:插入成功后将返回的记录通过request主题发送出去");
//                            String jsonString = JSON.toJSONString(product);
//                            logger.info("发送生产消息");
//                            mqttPushClient.publish(0,false,targetTopic,jsonString);
                        } else {
                            logger.error("没有查询到设备" + equipmentNo + "对应的记录");
                        }
                    }
                } else {
                    return;
                }

                //6:mqtt回调方法实时接收到设备生产服务发送的response主题的信息,将生产进度实时更新到数据库中

                //生产数量等于总数量时自动被移到product_history中
            }
        }, 0, 5000);
    }
}
