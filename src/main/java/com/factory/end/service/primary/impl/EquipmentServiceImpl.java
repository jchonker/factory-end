package com.factory.end.service.primary.impl;

import com.factory.end.dto.primary.EquipmentDto;
import com.factory.end.mapper.primary.EquipmentMapper;
import com.factory.end.model.primary.Equipment;
import com.factory.end.service.primary.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 14:52
 * @Version 1.0
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> findAll() {
        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        Iterable<Equipment> iEquipmentMapperAll = equipmentMapper.findAll();
        return (List<Equipment>) iEquipmentMapperAll;

    }


    @Override
    public List<Equipment> findAllOfLeisure() {
        List<Equipment> allOfLeisure = equipmentMapper.findAllOfLeisure();
        logger.info("空闲的设备记录:");
        logger.info(allOfLeisure.toString());
        return allOfLeisure;
    }

    @Override
    public void save(Equipment equipment) {
        equipmentMapper.save(equipment);
    }

    @Override
    public void updateEquStatusByEquNo(String equNo, Integer equStatus) {
        logger.info("service....根据设备号修改状态");
        logger.info("equNo:"+equNo+" equStatus:"+equStatus);
        equipmentMapper.updateEquStatusByEquNo(equNo,equStatus);
    }

    @Override
    public void updateAllowProduct(String equNo, Integer allowProduct) {
        logger.info("service...根据设备号修改是否允许生产");
        logger.info("equNo:"+equNo+" allowProduct:"+allowProduct);
        equipmentMapper.updateAllowProduct(equNo,allowProduct);
    }

    @Override
    public List<Equipment> findAllByAllStatus(Integer equStatus, Integer allowProduct, Integer autoModel) {
        List<Equipment> allByAllStatus = equipmentMapper.findAllByAllStatus(equStatus, allowProduct, autoModel);
        logger.info(allByAllStatus.toString());
        return allByAllStatus;
    }
}
