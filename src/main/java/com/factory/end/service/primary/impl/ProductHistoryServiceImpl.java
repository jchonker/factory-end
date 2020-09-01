package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.IProductHistoryMapper;
import com.factory.end.mapper.primary.IProductMapper;
import com.factory.end.model.primary.ProductHistory;
import com.factory.end.service.primary.ProductHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/29 17:47
 * @Version 1.0
 */
@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {
    @Autowired
    private IProductHistoryMapper iProductHistoryMapper;

    @Override
    public List<ProductHistory> findAll() {
        Iterable<ProductHistory> productHistories = iProductHistoryMapper.findAll();
        return (List<ProductHistory>)productHistories;
    }

    @Override
    public List<ProductHistory> findAllByOrderNo(Integer orderNo) {
        List<ProductHistory> allByOrderNo = iProductHistoryMapper.findAllByOrderNo(orderNo);
        return allByOrderNo;
    }

    @Override
    public List<ProductHistory> findAllByEquipmentNo(Integer equNo) {
        List<ProductHistory> allByEquipmentNo = iProductHistoryMapper.findAllByEquipmentNo(equNo);
        return allByEquipmentNo;
    }

    @Override
    public List<ProductHistory> findAllByCompProductDateBetween(String startTime, String endTime) {
        List<ProductHistory> allByCompleteTimeBetween = iProductHistoryMapper.findAllByCompProductDateBetween(startTime, endTime);
        return allByCompleteTimeBetween;
    }
}
