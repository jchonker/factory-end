package com.factory.end.service.primary;

import com.factory.end.model.primary.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 15:53
 * @Version 1.0
 */
@Service
public interface OrderService {
    /**
     * 新增记录
     * @param order
     */
    void save(Order order);

    /**
     * 批量插入
     * @param orderList
     */
    void saveAll(List<Order> orderList);

    /**
     * 根据订单编号查询
     * @param orderNo
     * @return
     */
    Order findOrderByOrderNo(Integer orderNo);

    /**
     * 根据产品名查询
     * @param lotName
     * @return
     */
    List<Order> findOrderByLotName(String lotName);

    /**
     * 根据产品型号进行查询
     * @param kindName
     * @return
     */
    List<Order> findOrderByKindName(String kindName);

    /**
     * 根据产品类型进行查询
     * @param kindClass
     * @return
     */
    List<Order> findOrderByKindClass(String kindClass);

    /**
     * 根据下单人员名进行查询
     * @param username
     * @return
     */
    List<Order> findOrdersByUserName(String username);

    /**
     * 根据时间段进行查询
     * @param startTime
     * @param endTime
     * @return
     */
    List<Order> findOrdersByOrderPlaceDate(String startTime,String endTime);

    /**
     * 根据确认标志进行查询
     * @param confirmFlag
     * @return
     */
    List<Order> findOrdersByOrderStatus(Integer confirmFlag);

    /**
     * 查询所有
     * @return
     */
    List<Order> findAll();


    /**
     * 根据订单号删除订单
     * @param orderNo
     * @return
     */
    boolean deleteOrderByOrderNo(Integer orderNo);

    /**
     * 根据下单人员删除订单
     * @param username
     * @return
     */
    boolean deleteOrdersByUserName(String username);

    /**
     * 根据订单号判断订单是否存在
     * @param orderNo
     * @return
     */
    boolean existsOrderByOrderNo(Integer orderNo);

    /**
     * 修改单条订单
     * @param order
     * @return
     */
    Order updateOrder(Order order);

    /**
     * 批量修改订单
     * @param orderList
     * @return
     */
    List<Order> updateOrders(List<Order> orderList);

}
