package com.factory.end.service.primary;

import com.factory.end.model.primary.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Map;

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
    void saveAll(List<Order> orderList) throws InterruptedException;

    /**
     * 根据订单编号查询
     * @param orderNo
     * @return
     */
    Order findOrderByOrderNo(String orderNo);

    /**
     * 根据订单状态数组查询
     * @param orderStatusArr
     * @return
     */
    List<Order> findOrdersByOrderStatusIn(Integer[] orderStatusArr);

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
    Integer deleteOrderByOrderNo(String orderNo);

    /**
     * 根据下单人员删除订单
     * @param username
     * @return
     */
    Integer deleteOrdersByUserName(String username);

    /**
     * 根据订单号判断订单是否存在
     * @param orderNo
     * @return
     */
    boolean existsOrderByOrderNo(String orderNo);

    /**
     * 根据下单人员名判断订单是否存在
     * @param username
     * @return
     */
    boolean existsOrdersByUserName(String username);

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

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Order> findAllByPage(Integer currentPage, Integer pageSize);

    /**
     * 根据订单号修改订单状态
     * @param orderNo
     * @param  orderStatus
     * @return
     */
    boolean updateOrderStatusByOrderNo(String orderNo,Integer orderStatus);

    /**
     * 复杂条件分页查询
     * @param order
     * @param orderStatusList
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Order> findByPage(Order order,List<Integer> orderStatusList, Integer currentPage, Integer pageSize);

    /**
     * BI查询
     * @return 返回订单状态和其数量
     */
    List<Map<String,Integer>> findOrderStatusAndCountByBI();

    /**
     * BI查询
     * @return 返回产品名和其数量
     */
    List<Map<String,Integer>> findLotNameAndCountByBI();

    /**
     * BI查询
     * @return 返回产品类型和其数量
     */
    List<Map<String,Integer>> findKindClassAndCountByBI();

    /**
     * BI查询
     * @return 返回下单人员名和其数量
     */
    List<Map<String,Integer>> findUserNameAndCountByBI();
}
