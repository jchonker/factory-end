package com.factory.end.service.primary;

import com.factory.end.model.primary.ProductHistory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/29 17:46
 * @Version 1.0
 */
@Service
public interface ProductHistoryService {
    /**
     * 查询所有生产完成数据
     * @return
     */
    List<ProductHistory> findAll();

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
     * @return
     */
    List<ProductHistory> findAllByCompProductDateBetween(String startTime,String endTime);

    /**
     * 复杂条件分页查询
     * @param productHistory
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<ProductHistory> findByPage(ProductHistory productHistory, Integer currentPage, Integer pageSize);
}
