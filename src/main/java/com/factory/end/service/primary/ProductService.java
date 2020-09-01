package com.factory.end.service.primary;

import com.factory.end.model.primary.Color;
import com.factory.end.model.primary.Product;
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
    List<Product> findAllByOrderNo(Integer orderNo);

    /**
     * 根据订单号查询所有数据
     * @param productNo
     * @return
     */
    List<Product> findAllByProductNo(Integer productNo);

    /**
     * 根据设备号查询所有数据
     * @param equNo
     * @return
     */
    List<Product> findAllByEquipmentNo(Integer equNo);

    /**
     * 根据生产开始时间查询所有数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<Product> findAllByStartDateBetween(String startDate,String endDate);

    /**
     * 根据生产完成标志查询所有数据
     * @param compProductFlg
     * @return
     */
    List<Product> findAllByCompProductFlg(Integer compProductFlg);


    /**
     * 添加
     * @param product
     */
    Product saveProduct(Product product);

    /**
     * 批量添加
     */
    List<Product> saveProducts(List<Product> productList);

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
    boolean existsByOrderNo(Integer orderNo);

    /**
     * 根据订单号删除订单数据
     * @param orderNo
     * @return
     */
    boolean deleteByOrderNo(Integer orderNo);
}
