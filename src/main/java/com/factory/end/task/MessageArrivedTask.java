package com.factory.end.task;

import com.alibaba.fastjson.JSON;
import com.factory.end.model.primary.Product;
import com.factory.end.service.primary.OrderService;
import com.factory.end.service.primary.ProductService;
import com.factory.end.util.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author jchonker
 * @Date 2020/9/10 10:46
 * @Version 1.0
 * mqtt收到消息后的处理类
 */
@Component
public class MessageArrivedTask {
    private Logger logger = LoggerFactory.getLogger(MessageArrivedTask.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    /**
     * 处理
     * @param string
     */
    public void process(String string){
        Product product = null;
        try {
            logger.info("将json字符串转换成Product实体类");
            product = JSON.parseObject(string, Product.class);
        } catch (Exception e) {
            logger.error("json字符串转换Product实体类失败");
            e.printStackTrace();
            return;
        }
        logger.info(product.toString());
        String productNo = product.getProductNo();
        Integer collectValue = product.getCollectValue();
        logger.info("根据生产号修改生产值");
        productService.updateCollectValueByProductNo(productNo,collectValue);
        if(product.getTargetValue().equals(product.getCollectValue())){
            logger.info("生产完成");
            logger.info("修改生产完成时间");
            long timeMillis = System.currentTimeMillis();
            Date date = new Date(timeMillis);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = simpleDateFormat.format(date);
            productService.updateCompProductDateByProductNo(productNo,dateStr);
            logger.info("修改订单状态");
            productService.updateOrderStatusByProductNo(productNo, OrderStatus.PRODUCECOMPLETE);

            //判断此分订单是否是大订单中的最后一个订单
            if(product.getLastSchedulingOfOrder().equals(1)){
                logger.info("最后一个分订单,从order表中删除orderNo等于"+product.getOrderNo()+"的记录...");
                orderService.deleteOrderByOrderNo(product.getOrderNo());
            }
        }
    }
}
