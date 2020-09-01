package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.ISchedulingMapper;
import com.factory.end.model.primary.Scheduling;
import com.factory.end.service.primary.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 17:05
 * @Version 1.0
 */
@Service
public class SchedulingServiceImpl implements SchedulingService {
    @Autowired
    private ISchedulingMapper iSchedulingMapper;

    @Override
    public List<Scheduling> findAll() {
        Iterable<Scheduling> all = iSchedulingMapper.findAll();
        return (List<Scheduling>)all;
    }

    @Override
    public List<Scheduling> findAllByOrderNo(Integer orderNo) {
        List<Scheduling> allByOrderNo = iSchedulingMapper.findAllByOrderNo(orderNo);
        return allByOrderNo;
    }

    @Override
    public List<Scheduling> findAllByEquipmentNo(Integer equNo) {
        List<Scheduling> allByEquipmentNo = iSchedulingMapper.findAllByEquipmentNo(equNo);
        return allByEquipmentNo;
    }

    @Override
    public List<Scheduling> findAllByKindName(String kindName) {
        List<Scheduling> allByKindName = iSchedulingMapper.findAllByKindName(kindName);
        return allByKindName;
    }

    @Override
    public List<Scheduling> findAllByKindClass(String kindClass) {
        List<Scheduling> allByKindClass = iSchedulingMapper.findAllByKindClass(kindClass);
        return allByKindClass;
    }

    @Override
    public List<Scheduling> findAllByLotName(String lotName) {
        List<Scheduling> allByLotName = iSchedulingMapper.findAllByLotName(lotName);
        return allByLotName;
    }

    @Override
    public List<Scheduling> findAllByUserName(String userName) {
        List<Scheduling> allByUserName = iSchedulingMapper.findAllByUserName(userName);
        return allByUserName;
    }

    @Override
    public List<Scheduling> findAllByOrderPlaceDateBetween(String startTime, String endTime) {
        List<Scheduling> allByOrderPlaceDateBetween = iSchedulingMapper.findAllByOrderPlaceDateBetween(startTime, endTime);
        return allByOrderPlaceDateBetween;
    }

    @Override
    public List<Scheduling> findAllByManuOrder(Integer manuOrder) {
        List<Scheduling> allByManuOrder = iSchedulingMapper.findAllByManuOrder(manuOrder);
        return allByManuOrder;
    }

    @Override
    public boolean deleteByOrderNo(Integer orderNo) {
        boolean delete = iSchedulingMapper.deleteByOrderNo(orderNo);
        return delete;
    }

    @Override
    public boolean deleteByUserName(String username) {
        boolean delete = iSchedulingMapper.deleteByUserName(username);
        return delete;
    }

    @Override
    public boolean existsByOrderNo(Integer orderNo) {
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

}
