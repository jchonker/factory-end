package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:34
 * @Version 1.0
 */
@Mapper
public interface ProductMapper extends CrudRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    /**
     * 根据订单号查询所有数据
     * @param orderNo
     * @return
     */
    List<Product> findAllByOrderNo(String orderNo);

    /**
     * 根据订单号查询所有数据
     * @param productNo
     * @return
     */
    List<Product> findAllByProductNo(String productNo);

    /**
     * 根据设备号查询所有数据
     * @param equNo
     * @return
     */
    List<Product> findAllByEquipmentNo(String equNo);

    /**
     * 根据生产开始时间段查询所有数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<Product> findAllByStartDateBetween(String startDate,String endDate);

    /**
     * 根据生产完成标志查询所有数据
     * @param orderStatus
     * findAllByCompProductFlg
     * @return
     */
    List<Product> findAllByOrderStatus(Integer orderStatus);

    /**
     * 根据订单号查询订单是否存在
     * @param orderNo
     * @return
     */
    boolean existsByOrderNo(String orderNo);

    /**
     * 根据产品好查询订单是否存在
     * @param productNo
     * @return
     */
    boolean existsByProductNo(String productNo);

    /**
     * 根据订单号删除订单数据
     * @param orderNo
     * @return
     */
    Integer deleteByOrderNo(String orderNo);

    /**
     * 根据生产号修改生产数量
     * @param productNo
     * @param collectValue
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_Product set Collect_Value = :collectValue where Product_No = :productNo",nativeQuery = true)
    void updateCollectValueByProductNo(@Param("productNo") String productNo,@Param("collectValue") Integer collectValue);

    /**
     * 根据生产号修改订单状态
     * @param productNo
     * @param orderStatus
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_Product set Order_Status = :orderStatus where Product_No = :productNo",nativeQuery = true)
    void updateOrderStatusByProductNo(@Param("productNo") String productNo,@Param("orderStatus") Integer orderStatus);

    /**
     * 根据产品号修改订单完成时间
     * @param productNo
     * @param compProductDate
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_Product set Comp_Product_Date = :compProductDate where Product_No = :productNo",nativeQuery = true)
    void updateCompProductDateByProductNo(@Param("productNo") String productNo,@Param("compProductDate") String compProductDate);



    /**
     * 根据订单号查询记录数量
     * @param orderNo
     * @return
     */
    @Query(value = "select count(*) from pro_Scheduling where Order_No = :orderNo",nativeQuery = true)
    Integer findCountByOrderNo(@Param("orderNo") String orderNo);
}
