package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.IEquipmentMapper;
import com.factory.end.mapper.primary.IEquipmentWorkersMapper;
import com.factory.end.model.primary.EquipmentWorkers;
import com.factory.end.service.primary.EquipmentWorkersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 15:15
 * @Version 1.0
 */
@Service
public class EquipmentWorkersServiceImpl implements EquipmentWorkersService {
    Logger logger = LoggerFactory.getLogger(EquipmentWorkersServiceImpl.class);

    @Autowired
    private IEquipmentWorkersMapper iEquipmentWorkersMapper;

    /**
     * 根据设备号查询职工编号（根据时间倒序排序取第一条）
     * @param equNo
     * @return
     */
    @Override
    public String selectWorkNoByENoOrderByWorkerDate(Integer equNo) {
        String workerNo = iEquipmentWorkersMapper.selectWorkNoByENoOrderByWorkerDate(equNo);
        logger.info(workerNo);
        return workerNo;
    }

    /**
     * 根据设备名查询所有历史操作员工
     * @param equNo
     * @return
     */
    @Override
    public List<EquipmentWorkers> findEquipmentWorkersByEquipmentNo(Integer equNo) {
        List<EquipmentWorkers> equipmentWorkersByEquipmentNo = iEquipmentWorkersMapper.findEquipmentWorkersByEquipmentNo(equNo);
        logger.info(equipmentWorkersByEquipmentNo.toString());
        return equipmentWorkersByEquipmentNo;
    }

    /**
     * 根据操作员工查询操作过的厉害设备名
     * @param workersNo
     * @return
     */
    @Override
    public List<EquipmentWorkers> findEquipmentWorkersByWorkersNo(String workersNo) {
        List<EquipmentWorkers> equipmentWorkersByWorkersNo = iEquipmentWorkersMapper.findEquipmentWorkersByWorkersNo(workersNo);
        logger.info(equipmentWorkersByWorkersNo.toString());
        return equipmentWorkersByWorkersNo;
    }

}
