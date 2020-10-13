package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Order;
import org.apache.ibatis.annotations.Mapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:33
 * @Version 1.0
 * JpaSpecificationExecutor做复杂查询
 */
@Mapper
public interface OrderMapper extends CrudRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
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
     * @param orderStatus
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_Order set Order_Status = :orderStatus where Order_No = :orderNo",nativeQuery = true)
    void updateOrderStatusByOrderNo(@Param("orderNo") String orderNo, @Param("orderStatus") Integer orderStatus);

    /**
     * BI查询
     * @return 返回订单状态和其数量
     */
    @Query(value = "select (SELECT pro_status_value FROM pro_Order_Status where pro_status_id = po.Order_Status) as 'name',COUNT(po.Order_Status)as 'value' from pro_Order po GROUP BY Order_Status",nativeQuery = true)
    List<Map<String,Integer>> findOrderStatusAndCountByBI();

    /**
     * BI查询
     * @return 返回产品名和其数量
     */
    @Query(value = "select Lot_Name as 'name',count(Lot_Name) as 'value' from pro_Order GROUP BY Lot_Name",nativeQuery = true)
    List<Map<String,Integer>> findLotNameAndCountByBI();

    /**
     * BI查询
     * @return 返回产品类型和其数量
     */
    @Query(value = "select Kind_Class as 'name', COUNT(Kind_Class) as 'value' from pro_Order GROUP BY Kind_Class",nativeQuery = true)
    List<Map<String,Integer>> findKindClassAndCountByBI();

    /**
     * BI查询
     * @return 返回下单人员名和其数量
     */
    @Query(value = "select User_Name as 'name', COUNT(User_Name) as 'value' from pro_Order GROUP BY User_Name",nativeQuery = true)
    List<Map<String,Integer>> findUserNameAndCountByBI();
}
