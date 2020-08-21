package com.factory.end.service;

import com.factory.end.dao.EquipmentDao;
import com.factory.end.dto.EquipmentDto;
import com.factory.end.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/14 10:00
 * @Version 1.0
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public List<EquipmentDto> findAll() {
        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        List<Equipment> equipmentList = (List<Equipment>)equipmentDao.findAll();

        EquipmentDto equipmentDto = null;
        if(equipmentList != null){
            System.out.println("有值...");
            for (Equipment equipment:equipmentList) {
                System.out.println(equipment.toString());
                equipmentDto = entityToDto(equipment);
                equipmentDtos.add(equipmentDto);
            }
            return  equipmentDtos;
        }
        return null;
    }

    /**
     * 将模板类转换为实体类
     * @param equipment
     * @return
     */
    private EquipmentDto entityToDto(Equipment equipment){
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setId(equipment.getId());
        equipmentDto.setBgcolor(equipment.getBgcolor());
        equipmentDto.setCurrentKey(equipment.getCurrentKey());
        equipmentDto.setCurrentKeyColor(equipment.getCurrentKeyColor());
        equipmentDto.setCurrentValue(equipment.getCurrentValue());
        equipmentDto.setCurrentValueColor(equipment.getCurrentValueColor());
        equipmentDto.setEquNumber(equipment.getEquNumber());
        equipmentDto.setEquNumberColor(equipment.getEquNumberColor());
        equipmentDto.setRealKey(equipment.getRealKey());
        equipmentDto.setRealKeyColor(equipment.getRealKeyColor());
        equipmentDto.setRealValue(equipment.getRealValue());
        equipmentDto.setRealValueColor(equipment.getRealValueColor());
        equipmentDto.setVarietiesKey(equipment.getVarietiesKey());
        equipmentDto.setVarietiesKeyColor(equipment.getVarietiesKeyColor());
        equipmentDto.setVarietiesValue(equipment.getVarietiesValue());
        equipmentDto.setVarietiesValueColor(equipment.getVarietiesValueColor());

        return equipmentDto;
    }
}
