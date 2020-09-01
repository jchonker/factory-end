package com.factory.end.service.primary;

import com.factory.end.dto.primary.EquipmentDto;
import com.factory.end.model.primary.Equipment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 14:50
 * @Version 1.0
 */
@Service
public interface EquipmentService {
    /**
     *
     * @return
     */
    List<EquipmentDto> findAll();


}
