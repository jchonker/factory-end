package com.factory.end.service.primary;

import com.factory.end.model.primary.Color;
import com.factory.end.model.primary.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 13:59
 * @Version 1.0
 */
@Service
public interface ProductService {
    /**
     * 查询所有数据
     * @return
     */
    List<Product> findAll();

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
     * 根据生产开始时间查询所有数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<Product> findAllByStartDateBetween(String startDate,String endDate);

    /**
     * 根据生产完成标志查询所有数据
     * @param orderStatus
     * @return
     */
    List<Product> findAllByOrderStatus(Integer orderStatus);


    /**
     * 添加
     * @param product
     */
    Product saveProduct(Product product);

    /**
     * 新增订单,传入订单号
     * @param id 订单号
     * @param lastSchedulingOfOrder 是否是大订单中的最后一条分订单
     * @return
     */
    Product saveProductBySchdulingId(Integer id,Integer lastSchedulingOfOrder);

    /**
     * 批量添加
     */
    List<Product> saveProducts(List<Product> productList);

    /**
     * 批量新增订单,传入订单列表
     * @param orderNoList
     * @return
     */
    List<Product> saveProductsByOrderNoList(List<String> orderNoList);

    /**
     * 根据id查询数据是否存在
     * @param id
     * @return
     */
    boolean existsById(Integer id);

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
     * 复杂条件分页查询
     * @param product
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Product> findByPage(Product product, Integer currentPage, Integer pageSize);

    /**
     * 根据生产号修改生产数量
     * @param productNo
     * @param collectValue
     */
    void updateCollectValueByProductNo(String productNo,Integer collectValue);

    /**
     *
     * @param productNo
     * @param orderStatus
     */
    void updateOrderStatusByProductNo(String productNo,Integer orderStatus);

    /**
     * 根据产品号修改订单完成时间
     * @param productNo
     * @param compProductDate
     */
    void updateCompProductDateByProductNo(@Param("productNo") String productNo, @Param("compProductDate") String compProductDate);

    /**
     * 根据订单号查询记录数量
     * @param orderNo
     * @return
     */
    Integer findCountByOrderNo(String orderNo);
}
