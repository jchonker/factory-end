package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.OrderMapper;
import com.factory.end.model.primary.Order;
import com.factory.end.service.primary.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author jchonker
 * @Date 2020/8/27 17:03
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper iOrderMapper;

    /**
     * 获取订单id
     * 加锁,防止并发获取到相同的订单id
     * @return
     */
    public synchronized String getOrderNoString() throws InterruptedException {
        //延迟1毫秒，防止获取到相同的时间戳
        Thread.sleep(1);
        //Order首字母字母+时间戳
        return "O"+System.currentTimeMillis();
    }

    /**
     * 获取系统预期完成时间
     * @param targetValue 目标产量
     * @return
     */
    public synchronized String getCompExceptDate(Integer targetValue){
        long timeMillis = System.currentTimeMillis();
        long compTimeMillis = timeMillis + 5 * 1000 * targetValue;
        Date date = new Date(compTimeMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String compExceptionDateStr = simpleDateFormat.format(date);
        logger.info("获取系统预期完成时间:"+compExceptionDateStr);
        return compExceptionDateStr;
    }

    @Override
    public void save(Order order) {
        try {
            order.setOrderNo(getOrderNoString());
            order.setCompExpectDate(getCompExceptDate(order.getTargetValue()));
            //订单完成时间设置为空
            order.setCompOrderDate(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iOrderMapper.save(order);
    }

    @Override
    public void saveAll(List<Order> orderList) throws InterruptedException {
        List<Order> newOrderList = new ArrayList<>();
        for (Order order : orderList) {
            order.setOrderNo(getOrderNoString());
            order.setCompExpectDate(getCompExceptDate(order.getTargetValue()));
            newOrderList.add(order);
        }
        iOrderMapper.saveAll(newOrderList);
    }

    @Override
    public Order findOrderByOrderNo(String orderNo) {
        Order orderByOrderNo = iOrderMapper.findOrderByOrderNo(orderNo);
        logger.info(orderByOrderNo.toString());
        return orderByOrderNo;
    }

    @Override
    public List<Order> findOrdersByOrderStatusIn(Integer[] orderStatusArr) {
        List<Order> ordersByOrderStatusIn = iOrderMapper.findOrdersByOrderStatusIn(orderStatusArr);
        for (Order order : ordersByOrderStatusIn) {
            logger.info(order.toString());
        }
        return ordersByOrderStatusIn;
    }

    @Override
    public List<Order> findOrderByLotName(String lotName) {
        List<Order> orderByLotName = iOrderMapper.findOrderByLotName(lotName);
        lotName.indexOf(orderByLotName.toString());
        return orderByLotName;
    }

    @Override
    public List<Order> findOrderByKindName(String kindName) {
        List<Order> orderByKindName = iOrderMapper.findOrderByKindName(kindName);
        logger.info(orderByKindName.toString());
        return orderByKindName;
    }

    @Override
    public List<Order> findOrderByKindClass(String kindClass) {
        List<Order> orderByKindClass = iOrderMapper.findOrderByKindClass(kindClass);
        logger.info(orderByKindClass.toString());
        return orderByKindClass;
    }

    @Override
    public List<Order> findOrdersByUserName(String username) {
        List<Order> orderByUserName = iOrderMapper.findOrdersByUserName(username);
        logger.info(orderByUserName.toString());
        return orderByUserName;
    }

    @Override
    public List<Order> findOrdersByOrderPlaceDate(String startTime, String endTime) {
        List<Order> ordersByOrderPlaceDateBetween = iOrderMapper.findOrdersByOrderPlaceDateBetween(startTime, endTime);
        logger.info(ordersByOrderPlaceDateBetween.toString());
        return ordersByOrderPlaceDateBetween;
    }

    @Override
    public List<Order> findOrdersByOrderStatus(Integer confirmFlag) {
        List<Order> ordersByConfirmOrderFlg = iOrderMapper.findOrdersByOrderStatus(confirmFlag);
        logger.info(ordersByConfirmOrderFlg.toString());
        return ordersByConfirmOrderFlg;
    }

    @Override
    public List<Order> findAll() {
        Iterable<Order> iOrderMapperAll = iOrderMapper.findAll();
        logger.info(iOrderMapperAll.toString());
        return (List<Order>)iOrderMapperAll;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteOrderByOrderNo(String orderNo) {
        Integer i = iOrderMapper.deleteOrderByOrderNo(orderNo);
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteOrdersByUserName(String username) {
        Integer i = iOrderMapper.deleteOrdersByUserName(username);
        return i;
    }

    @Override
    public boolean existsOrderByOrderNo(String orderNo) {
        boolean flag = iOrderMapper.existsOrderByOrderNo(orderNo);
        return flag;
    }

    @Override
    public boolean existsOrdersByUserName(String username) {
        boolean flag = iOrderMapper.existsOrdersByUserName(username);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order updateOrder(Order order) {
//        System.out.println("先根据orderNo删除旧的数据:"+order.getOrderNo());
//        iOrderMapper.deleteOrderByOrderNo(order.getOrderNo());
        Order updateOrder = iOrderMapper.save(order);
        return updateOrder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Order> updateOrders(List<Order> orderList) {
//        System.out.println("先根据orderNo删除旧的数据:");
//        for(Order order : orderList){
//            System.out.println(order.getOrderNo());
//            iOrderMapper.deleteOrderByOrderNo(order.getOrderNo());
//        }
        Iterable<Order> orders = iOrderMapper.saveAll(orderList);
        return (List<Order>)orders;
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public Page<Order> findAllByPage(Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        logger.info(pageable.toString());
        return iOrderMapper.selectAll(pageable);
    }

    /**
     * 根据订单号修改订单状态
     * @param orderNo
     * @param orderStatus
     * @return
     */
    @Override
    public boolean updateOrderStatusByOrderNo(String orderNo,Integer orderStatus) {
        iOrderMapper.updateOrderStatusByOrderNo(orderNo,orderStatus);
        return true;
    }

    /**
     * 复杂条件分页查询
     * @param order
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public Page<Order> findByPage(Order order,List<Integer> orderStatusList, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Specification<Order> specification = (root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();
            if(order.getOrderNo() != null && !"".equals(order.getOrderNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("orderNo").as(String.class), order.getOrderNo());
                list.add(predicate);
            }
            if(order.getKindName() != null && !"".equals(order.getKindName())){
                Predicate predicate = criteriaBuilder.equal(root.get("kindName").as(String.class), order.getKindName());
                list.add(predicate);
            }
            if(order.getKindClass() != null && !"".equals(order.getKindClass())){
                Predicate predicate = criteriaBuilder.equal(root.get("kindClass").as(String.class), order.getKindClass());
                list.add(predicate);
            }
            if(order.getLotName() != null && !"".equals(order.getLotName())){
                Predicate predicate = criteriaBuilder.equal(root.get("lotName").as(String.class), order.getLotName());
                list.add(predicate);
            }
            if (order.getUserName() != null && !"".equals(order.getUserName())){
                Predicate predicate = criteriaBuilder.equal(root.get("userName").as(String.class), order.getUserName());
                list.add(predicate);
            }
            if(order.getProjectNo() != null && !"".equals(order.getProjectNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("projectNo").as(String.class),order.getProjectNo());
            }

//            if(order.getOrderStatus() != null && !"".equals(order.getOrderStatus())){
//                logger.info("添加订单状态条件");
//                Predicate predicate = criteriaBuilder.equal(root.get("orderStatus").as(String.class), order.getOrderStatus());
//                list.add(predicate);
//            }

            if(orderStatusList != null && orderStatusList.size() != 0){
                Expression<Integer> exp = root.get("orderStatus");
                list.add(exp.in(orderStatusList));    //在orderStatusList中添加所有id实现in查询
            }

            //new一个数组作为最终返回值的条件
            Predicate[] predicates = new Predicate[list.size()];
            //将list直接转换成数组
            list.toArray(predicates);
            return criteriaBuilder.and(list.toArray(predicates));
            //return criteriaBuilder.and(list.toArray(new javax.persistence.criteria.Predicate[0]));
        };
        //复杂条件查询
        return iOrderMapper.findAll(specification,pageable);
    }

    @Override
    public List<Map<String,Integer>> findOrderStatusAndCountByBI() {
        List<Map<String,Integer>> orderStatusAndCountByBI = iOrderMapper.findOrderStatusAndCountByBI();
        logger.info(orderStatusAndCountByBI.toString());
        return orderStatusAndCountByBI;
    }

    @Override
    public List<Map<String, Integer>> findLotNameAndCountByBI() {
        List<Map<String, Integer>> lotNameAndCountByBI = iOrderMapper.findLotNameAndCountByBI();
        logger.info(lotNameAndCountByBI.toString());
        return lotNameAndCountByBI;
    }

    @Override
    public List<Map<String, Integer>> findKindClassAndCountByBI() {
        List<Map<String, Integer>> kindClassAndCountByBI = iOrderMapper.findKindClassAndCountByBI();
        logger.info(kindClassAndCountByBI.toString());
        return kindClassAndCountByBI;
    }

    @Override
    public List<Map<String, Integer>> findUserNameAndCountByBI() {
        List<Map<String, Integer>> userNameAndCountByBI = iOrderMapper.findUserNameAndCountByBI();
        logger.info(userNameAndCountByBI.toString());
        return userNameAndCountByBI;
    }
}
