package com.factory.end.service.second.impl;

import com.factory.end.mapper.second.MyEquipmentMapper;
import com.factory.end.dto.second.MyEquipmentDto;
import com.factory.end.model.second.MyEquipment;
import com.factory.end.service.second.MyEquipmentService;
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
public class MyEquipmentServiceImpl implements MyEquipmentService {
    @Autowired
    private MyEquipmentMapper iMyEquipmentMapper;

    @Override
    public List<MyEquipmentDto> findAll() {
        List<MyEquipmentDto> myEquipmentDtos = new ArrayList<>();
        List<MyEquipment> myEquipmentList = (List<MyEquipment>) iMyEquipmentMapper.findAll();

        MyEquipmentDto myEquipmentDto = null;
        if(myEquipmentList != null){
            System.out.println("有值...");
            for (MyEquipment myEquipment : myEquipmentList) {
                System.out.println(myEquipment.toString());
                myEquipmentDto = entityToDto(myEquipment);
                myEquipmentDtos.add(myEquipmentDto);
            }
            return myEquipmentDtos;
        }
        return null;
    }

    /**
     * 将模板类转换为实体类
     * @param myEquipment
     * @return
     */
    private MyEquipmentDto entityToDto(MyEquipment myEquipment){
        MyEquipmentDto myEquipmentDto = new MyEquipmentDto();
        myEquipmentDto.setId(myEquipment.getId());
        myEquipmentDto.setBgcolor(myEquipment.getBgcolor());
        myEquipmentDto.setCurrentKey(myEquipment.getCurrentKey());
        myEquipmentDto.setCurrentKeyColor(myEquipment.getCurrentKeyColor());
        myEquipmentDto.setCurrentValue(myEquipment.getCurrentValue());
        myEquipmentDto.setCurrentValueColor(myEquipment.getCurrentValueColor());
        myEquipmentDto.setEquNumber(myEquipment.getEquNumber());
        myEquipmentDto.setEquNumberColor(myEquipment.getEquNumberColor());
        myEquipmentDto.setRealKey(myEquipment.getRealKey());
        myEquipmentDto.setRealKeyColor(myEquipment.getRealKeyColor());
        myEquipmentDto.setRealValue(myEquipment.getRealValue());
        myEquipmentDto.setRealValueColor(myEquipment.getRealValueColor());
        myEquipmentDto.setVarietiesKey(myEquipment.getVarietiesKey());
        myEquipmentDto.setVarietiesKeyColor(myEquipment.getVarietiesKeyColor());
        myEquipmentDto.setVarietiesValue(myEquipment.getVarietiesValue());
        myEquipmentDto.setVarietiesValueColor(myEquipment.getVarietiesValueColor());

        return myEquipmentDto;
    }
}
