package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.OrderMapper;
import com.factory.end.mapper.primary.SchedulingMapper;
import com.factory.end.model.primary.Scheduling;
import com.factory.end.service.primary.SchedulingService;
import com.factory.end.util.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author jchonker
 * @Date 2020/8/28 17:05
 * @Version 1.0
 */
@Service
public class SchedulingServiceImpl implements SchedulingService {
    Logger logger = LoggerFactory.getLogger(SchedulingServiceImpl.class);

    @Autowired
    private SchedulingMapper iSchedulingMapper;

    @Autowired
    private OrderMapper iOrderMapper;

    @Override
    public Scheduling findById(Integer id) {
        Optional<Scheduling> optional = iSchedulingMapper.findById(id);
        if(optional != null && optional.isPresent()){
            Scheduling scheduling = optional.get();
            return scheduling;
        }
        else {
            return null;
        }
    }

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
    public void deleteById(Integer id) {
        boolean exists = iSchedulingMapper.existsById(id);
        if(exists){
            try {
                iSchedulingMapper.deleteById(id);
            } catch (Exception e) {
                logger.error("根据id删除scheduling表中记录出错!!!");
                e.printStackTrace();
            }
        }
        else {
            logger.info("schduling表中不存在id为"+id+"的记录");
        }
    }

    @Override
    public boolean existsByUserName(String username) {
        boolean exists = iSchedulingMapper.existsByUserName(username);
        return exists;
    }

    @Override
    public void saveScheduling(Scheduling scheduling) {
        Scheduling save = iSchedulingMapper.save(scheduling);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSchedulings(List<Scheduling> schedulingList) {
        //保存
        iSchedulingMapper.saveAll(schedulingList);
        //修改order表中的对应的订单的状态为排单中状态
        Integer orderStatus = OrderStatus.SCHEDULING;
        logger.info("要修改的订单状态:"+orderStatus);
        String orderNo = schedulingList.get(0).getOrderNo();
        logger.info("订单号:"+orderNo);
        iOrderMapper.updateOrderStatusByOrderNo(orderNo,orderStatus);
    }

    @Override
    public Scheduling findOneByEquipmentNoOrderByManuOrder(String equNo) {
        Scheduling oneByEquipmentNoOrderByManuOrder = iSchedulingMapper.findOneByEquipmentNoOrderByManuOrder(equNo);
        if(oneByEquipmentNoOrderByManuOrder != null){
            return oneByEquipmentNoOrderByManuOrder;
        }
        else {
            return null;
        }
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
