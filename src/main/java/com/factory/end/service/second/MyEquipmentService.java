package com.factory.end.service.second;

import com.factory.end.dto.second.MyEquipmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/14 9:58
 * @Version 1.0
 */
@Service
public interface MyEquipmentService {
    /**
     * 查询所有数据
     * @return
     */
    List<MyEquipmentDto> findAll();

}
