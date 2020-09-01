package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Color;
import com.factory.end.model.primary.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:34
 * @Version 1.0
 */
@Mapper
public interface IProductMapper extends CrudRepository<Product,Integer> {
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
     * 根据生产开始时间段查询所有数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<Product> findAllByStartDateBetween(String startDate,String endDate);

    /**
     * 根据生产完成标志查询所有数据
     * @param compProductFlg
     * findAllByCoAndCompProductFlg
     * @return
     */
    List<Product> findAllByCompProductFlg(Integer compProductFlg);

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
