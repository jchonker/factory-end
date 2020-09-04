package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:33
 * @Version 1.0
 * JpaSpecificationExecutor做复杂查询
 */
@Mapper
public interface IOrderMapper extends CrudRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Query(value = "select * from pro_Order",nativeQuery = true)
    Page<Order> selectAll(Pageable pageable);

    /**
     * 根据orderNo查询
     * @param orderNo
     * @return
     */
    Order findOrderByOrderNo(String orderNo);

    /**
     * 根据产品名查询
     * @param lotName
     * @return
     */
    List<Order> findOrderByLotName(String lotName);

    /**
     * 根据产品型号查询
     * @param kindName
     * @return
     */
    List<Order> findOrderByKindName(String kindName);

    /**
     * 根据产品类型查询
     * @param kindClass
     * findOrderByKindClass
     * @return
     */
    List<Order> findOrderByKindClass(String kindClass);

    /**
     * 根据下单人员查询
     * @param username
     * @return
     */
    List<Order> findOrdersByUserName(String username);

    /**
     * 根据订单确认时间段进行查询
     * @param startTime
     * @param endTime
     * findOrdersByOrderPlaceDateBetween
     * @return
     */
    List<Order> findOrdersByOrderPlaceDateBetween(String startTime,String endTime);

    /**
     * 根据订单确认标志进行查询
     * @param confirmFlag
     * findOrdersByConfirmOrderFlg
     * @return
     */
    List<Order> findOrdersByOrderStatus(Integer confirmFlag);

    /**
     * 根据订单号删除订单
     * @param orderNo
     * @return
     */
    @Modifying
    Integer deleteOrderByOrderNo(String orderNo);

    /**
     * 根据下单人员删除订单
     * @param username
     * @return
     */
    @Modifying
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
     * 根据订单号修改订单状态
     * @param orderNo
     * @return
     */
    @Query(value = "update Order_Status from pro_Order where Order_No = :orderNo",nativeQuery = true)
    boolean updateOrderStatusByOrderNo(@Param("orderNo") String orderNo);
}
