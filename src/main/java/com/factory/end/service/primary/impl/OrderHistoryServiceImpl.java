package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.OrderHistoryMapper;
import com.factory.end.service.primary.OrderHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author jchonker
 * @Date 2020/10/16 14:38
 * @Version 1.0
 */
@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private Logger logger = LoggerFactory.getLogger(OrderHistoryServiceImpl.class);

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    @Override
    public List<Map<String, Map<String, Map<String, Integer>>>> findOrderHistoriesGroupByCompOrderDate() {
        List<Map<String, Map<String, Map<String, Integer>>>> orderHistoriesGroupByCompOrderDate = orderHistoryMapper.findOrderHistoriesGroupByCompOrderDate();
        if(orderHistoriesGroupByCompOrderDate != null){
            logger.info(orderHistoriesGroupByCompOrderDate.toString());
            return orderHistoriesGroupByCompOrderDate;
        }
        return null;
    }
}
