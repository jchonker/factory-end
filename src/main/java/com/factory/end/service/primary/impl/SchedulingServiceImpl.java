package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.ISchedulingMapper;
import com.factory.end.model.primary.Scheduling;
import com.factory.end.service.primary.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Date 2020/8/28 17:05
 * @Version 1.0
 */
@Service
public class SchedulingServiceImpl implements SchedulingService {
    Logger logger = LoggerFactory.getLogger(SchedulingServiceImpl.class);

    @Autowired
    private ISchedulingMapper iSchedulingMapper;

    @Override
    public List<Scheduling> findAll() {
        Iterable<Scheduling> all = iSchedulingMapper.findAll();
        logger.info(all.toString());
        return (List<Scheduling>)all;
    }

    @Override
    public Scheduling findByOrderNo(String orderNo) {
        Scheduling scheduling = iSchedulingMapper.findByOrderNo(orderNo);
        logger.info(scheduling.toString());
        return scheduling;
    }

    @Override
    public List<Scheduling> findAllByEquipmentNo(String equNo) {
        List<Scheduling> allByEquipmentNo = iSchedulingMapper.findAllByEquipmentNo(equNo);
        logger.info(allByEquipmentNo.toString());
        return allByEquipmentNo;
    }

    @Override
    public List<Scheduling> findAllByKindName(String kindName) {
        List<Scheduling> allByKindName = iSchedulingMapper.findAllByKindName(kindName);
        logger.info(allByKindName.toString());
        return allByKindName;
    }

    @Override
    public List<Scheduling> findAllByKindClass(String kindClass) {
        List<Scheduling> allByKindClass = iSchedulingMapper.findAllByKindClass(kindClass);
        logger.info(allByKindClass.toString());
        return allByKindClass;
    }

    @Override
    public List<Scheduling> findAllByLotName(String lotName) {
        List<Scheduling> allByLotName = iSchedulingMapper.findAllByLotName(lotName);
        logger.info(allByLotName.toString());
        return allByLotName;
    }

    @Override
    public List<Scheduling> findAllByUserName(String userName) {
        List<Scheduling> allByUserName = iSchedulingMapper.findAllByUserName(userName);
        logger.info(allByUserName.toString());
        return allByUserName;
    }

    @Override
    public List<Scheduling> findAllByOrderPlaceDateBetween(String startTime, String endTime) {
        List<Scheduling> allByOrderPlaceDateBetween = iSchedulingMapper.findAllByOrderPlaceDateBetween(startTime, endTime);
        logger.info(allByOrderPlaceDateBetween.toString());
        return allByOrderPlaceDateBetween;
    }

    @Override
    public List<Scheduling> findAllByManuOrder(Integer manuOrder) {
        List<Scheduling> allByManuOrder = iSchedulingMapper.findAllByManuOrder(manuOrder);
        logger.info(allByManuOrder.toString());
        return allByManuOrder;
    }

    @Override
    public Integer deleteByOrderNo(String orderNo) {
        Integer delete = iSchedulingMapper.deleteByOrderNo(orderNo);
        return delete;
    }

    @Override
    public Integer deleteByUserName(String username) {
        Integer delete = iSchedulingMapper.deleteByUserName(username);
        return delete;
    }

    @Override
    public boolean existsByOrderNo(String orderNo) {
        boolean exists = iSchedulingMapper.existsByOrderNo(orderNo);
        return exists;
    }

    @Override
    public boolean existsByUserName(String username) {
        boolean exists = iSchedulingMapper.existsByUserName(username);
        return exists;
    }

    @Override
    public void saveScheduling(Scheduling scheduling) {
         iSchedulingMapper.save(scheduling);
    }

    @Override
    public void saveSchedulings(List<Scheduling> schedulingList) {
        iSchedulingMapper.saveAll(schedulingList);
    }

    @Override
    public Scheduling findOneByEquipmentNoOrderByManuOrder(String equNo) {
        Scheduling oneByEquipmentNoOrderByManuOrder = iSchedulingMapper.findOneByEquipmentNoOrderByManuOrder(equNo);
        return oneByEquipmentNoOrderByManuOrder;
    }

    /**
     * 复杂条件分页查询
     * @param scheduling
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public Page<Scheduling> findByPage(Scheduling scheduling, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        Specification<Scheduling> specification = (root, query, criteriaBuilder) ->{
            //存放所有条件
            List<Predicate> list = new ArrayList<>();
            if(scheduling.getOrderNo() != null && !"".equals(scheduling.getOrderNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("orderNo").as(String.class),scheduling.getOrderNo());
                list.add(predicate);
            }
            if(scheduling.getEquipmentNo() != null && !"".equals(scheduling.getEquipmentNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("equipmentNo").as(String.class),scheduling.getOrderNo());
                list.add(predicate);
            }
            if(scheduling.getKindName() != null && !"".equals(scheduling.getKindName())){
                Predicate predicate = criteriaBuilder.equal(root.get("kindName").as(String.class),scheduling.getKindName());
                list.add(predicate);
            }
            if(scheduling.getKindClass() != null && !"".equals(scheduling.getKindClass())){
                Predicate predicate = criteriaBuilder.equal(root.get("kindClass").as(String.class),scheduling.getKindClass());
                list.add(predicate);
            }
            if(scheduling.getLotName() != null &&!"".equals(scheduling.getLotName())){
                Predicate predicate = criteriaBuilder.equal(root.get("lotName").as(String.class),scheduling.getLotName());
                list.add(predicate);
            }
            if(scheduling.getUserName() != null && !"".equals(scheduling.getUserName())){
                Predicate predicate = criteriaBuilder.equal(root.get("userName").as(String.class),scheduling.getUserName());
                list.add(predicate);
            }
            //new一个数组作为最终返回值的条件
            Predicate[] predicates = new Predicate[list.size()];
            //将list直接转换成数组
            list.toArray(predicates);
            return criteriaBuilder.and(list.toArray(predicates));
        };
        //复杂条件查询
        return iSchedulingMapper.findAll(specification,pageable);
    }
}
