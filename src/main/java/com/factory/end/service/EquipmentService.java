package com.factory.end.service;

import com.factory.end.dto.EquipmentDto;
import com.factory.end.model.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/14 9:58
 * @Version 1.0
 */
@Service
public interface EquipmentService {
    /**
     * 查询所有数据
     * @return
     */
    List<EquipmentDto> findAll();

}
