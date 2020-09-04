package com.factory.end.service.primary.impl;

import com.factory.end.dto.primary.EquipmentDto;
import com.factory.end.mapper.primary.IEquipmentMapper;
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
    private IEquipmentMapper iEquipmentMapper;

    @Override
    public List<Equipment> findAll() {
        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        Iterable<Equipment> iEquipmentMapperAll = iEquipmentMapper.findAll();
        return (List<Equipment>) iEquipmentMapperAll;

//        EquipmentDto equipmentDto;
//        if(iEquipmentMapperAll!=null){
//            logger.info(iEquipmentMapperAll.toString());
//            for(Equipment equipment:iEquipmentMapperAll){
//                equipmentDto = entityToDto(equipment);
//                equipmentDtos.add(equipmentDto);
//            }
//            return equipmentDtos;
//        }
//        return null;
    }

    /**
     * Status转换成StatusDto类
     * @param equipment
     * @return
     */
//    private EquipmentDto entityToDto(Equipment equipment){
//        EquipmentDto equipmentDto = new EquipmentDto();
//        equipmentDto.setEquipmentName(equipment.getEquipmentName());
//        equipmentDto.setEquipmentNo(equipment.getEquipmentNo());
//        equipmentDto.setProductionType(equipment.getProductionType());
//        return equipmentDto;
//    }
}