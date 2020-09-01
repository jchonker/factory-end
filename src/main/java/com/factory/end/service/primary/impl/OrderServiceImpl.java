package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.IOrderMapper;
import com.factory.end.model.primary.Order;
import com.factory.end.service.primary.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author jchonker
 * @Date 2020/8/27 17:03
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private IOrderMapper iOrderMapper;

    /**
     * 获取订单id
     * @return
     */
    public String getOrderNoString() throws InterruptedException {
        //延迟1毫秒，防止获取到相同的时间戳
        Thread.sleep(1);
        return "F"+System.currentTimeMillis();
    }

    @Override
    public void save(Order order) {
        try {
            order.setOrderNo(getOrderNoString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iOrderMapper.save(order);
    }

    @Override
    public void saveAll(List<Order> orderList) {
        iOrderMapper.saveAll(orderList);
    }

    @Override
    public Order findOrderByOrderNo(Integer orderNo) {
        Order orderByOrderNo = iOrderMapper.findOrderByOrderNo(orderNo);
        return orderByOrderNo;
    }

    @Override
    public List<Order> findOrderByLotName(String lotName) {
        List<Order> orderByLotName = iOrderMapper.findOrderByLotName(lotName);
        return orderByLotName;
    }

    @Override
    public List<Order> findOrderByKindName(String kindName) {
        List<Order> orderByKindName = iOrderMapper.findOrderByKindName(kindName);
        return orderByKindName;
    }

    @Override
    public List<Order> findOrderByKindClass(String kindClass) {
        List<Order> orderByKindClass = iOrderMapper.findOrderByKindClass(kindClass);
        return orderByKindClass;
    }

    @Override
    public List<Order> findOrdersByUserName(String username) {
        List<Order> orderByUserName = iOrderMapper.findOrdersByUserName(username);
        return orderByUserName;
    }

    @Override
    public List<Order> findOrdersByOrderPlaceDate(String startTime, String endTime) {
        List<Order> ordersByOrderPlaceDateBetween = iOrderMapper.findOrdersByOrderPlaceDateBetween(startTime, endTime);
        return ordersByOrderPlaceDateBetween;
    }

    @Override
    public List<Order> findOrdersByOrderStatus(Integer confirmFlag) {
        List<Order> ordersByConfirmOrderFlg = iOrderMapper.findOrdersByOrderStatus(confirmFlag);
        return ordersByConfirmOrderFlg;
    }

    @Override
    public List<Order> findAll() {
        Iterable<Order> iOrderMapperAll = iOrderMapper.findAll();
        return (List<Order>)iOrderMapperAll;
    }

    @Override
    public boolean deleteOrderByOrderNo(Integer orderNo) {
        boolean flag = iOrderMapper.deleteOrderByOrderNo(orderNo);
        return flag;
    }

    @Override
    public boolean deleteOrdersByUserName(String username) {
        boolean flag = iOrderMapper.deleteOrdersByUserName(username);
        return flag;
    }

    @Override
    public boolean existsOrderByOrderNo(Integer orderNo) {
        boolean exsit = iOrderMapper.existsOrderByOrderNo(orderNo);
        return exsit;
    }

    @Override
    public Order updateOrder(Order order) {
        Order updateOrder = iOrderMapper.save(order);
        return updateOrder;
    }

    @Override
    public List<Order> updateOrders(List<Order> orderList) {
        Iterable<Order> orders = iOrderMapper.saveAll(orderList);
        return (List<Order>)orders;
    }

}
