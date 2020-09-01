package com.factory.end.dto.primary;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * @Author jchonker
 * @Date 2020/8/26 13:38
 * @Version 1.0
 */
@Data
public class EquipmentWorkersDto {
    private Integer id;

    private Integer equipmentNo;

    private  String workersNo;

    private String workersDate;
}
