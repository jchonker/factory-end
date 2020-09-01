package com.factory.end.dto.primary;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * @Author jchonker
 * @Date 2020/8/26 14:10
 * @Version 1.0
 * 待生产表(排好的单)
 */
@Data
public class SchedulingDto {
    private Integer id;

    /**
     * 订单编号
     */
    private Integer orderNo;

    /**
     * 设备号
     */
    private Integer equipmentNo;

    /**
     * 产品名
     */
    private String lotName;

    /**
     * 产品型号
     */
    private String kindName;

    /**
     * 产品类型
     */
    private String kindClass;

    /**
     * 计划值
     */
    private Integer targetValue;

    /**
     * 下单人员名
     */
    private String userName;

    /**
     * 提交订单时间
     */
    private String orderPlaceDate;

    /**
     * 预期完成时间
     */
    private String compExpectDate;

    /**
     * 生产顺序(生产的优先顺序)
     */
    private Integer manuOrder;

    /**
     * 更新日期时间
     */
    private String lastUpdate;
}
