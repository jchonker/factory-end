package com.factory.end.mapper.primary;

import com.factory.end.model.primary.ProductHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/29 17:44
 * @Version 1.0
 */
@Mapper
public interface ProductHistoryMapper extends CrudRepository<ProductHistory,Integer>, JpaSpecificationExecutor<ProductHistory> {

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    List<ProductHistory> findAllByOrderNo(Integer orderNo);

    /**
     * 根据设备号查询
     * @param equNo
     * @return
     */
    List<ProductHistory> findAllByEquipmentNo(String equNo);

    /**
     * 根据订单完成时间段查询
     * @param startTime
     * @param endTime
     * findAllByCompleteTimeBetween
     * @return
     */
    List<ProductHistory> findAllByCompProductDateBetween(String startTime,String endTime);

}
