package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.ProductHistoryMapper;
import com.factory.end.model.primary.ProductHistory;
import com.factory.end.service.primary.ProductHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/29 17:47
 * @Version 1.0
 */
@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {
    @Autowired
    private ProductHistoryMapper iProductHistoryMapper;

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
    public List<ProductHistory> findAllByEquipmentNo(String equNo) {
        List<ProductHistory> allByEquipmentNo = iProductHistoryMapper.findAllByEquipmentNo(equNo);
        return allByEquipmentNo;
    }

    @Override
    public List<ProductHistory> findAllByCompProductDateBetween(String startTime, String endTime) {
        List<ProductHistory> allByCompleteTimeBetween = iProductHistoryMapper.findAllByCompProductDateBetween(startTime, endTime);
        return allByCompleteTimeBetween;
    }

    @Override
    public Page<ProductHistory> findByPage(ProductHistory productHistory, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Specification<ProductHistory> specification = (root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();
            if(productHistory.getOrderNo() != null && !"".equals(productHistory.getOrderNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("orderNo").as(String.class), productHistory.getOrderNo());
                list.add(predicate);
            }
            if(productHistory.getProductNo() != null && !"".equals(productHistory.getProductNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("productNo").as(String.class), productHistory.getProductNo());
                list.add(predicate);
            }
            if(productHistory.getEquipmentNo() != null && !"".equals(productHistory.getEquipmentNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("equipmentNo").as(String.class), productHistory.getEquipmentNo());
                list.add(predicate);
            }
            if (productHistory.getUserName() != null && !"".equals(productHistory.getUserName())){
                Predicate predicate = criteriaBuilder.equal(root.get("userName").as(String.class), productHistory.getUserName());
                list.add(predicate);
            }
            //new一个数组作为最终返回值的条件
            Predicate[] predicates = new Predicate[list.size()];
            //将list直接转换成数组
            list.toArray(predicates);
            return criteriaBuilder.and(list.toArray(predicates));
            //return criteriaBuilder.and(list.toArray(new javax.persistence.criteria.Predicate[0]));
        };
        //复杂条件查询
        return iProductHistoryMapper.findAll(specification,pageable);
    }
}
