package com.factory.end.dto.primary;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * @Author jchonker
 * @Date 2020/8/26 13:43
 * @Version 1.0
 * 订单
 */
@Data
public class OrderDto {
    private Integer id;

    /**
     * 产品编号
     */
    private String orderNo;

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
     * 系统预期完成时间
     */
    private String compExpectDate;

    /**
     * 下单人员名
     */
    private String userName;

    /**
     * 提交订单时间
     */
    private String orderPlaceDate;

    /**
     * 订单确认标志 1:未确认 2:确认
     */
    private Integer orderStatus;

    /**
     * 更新日期时间
     */
    private String lastUpdate;

    /**
     * 用户期望完成时间
     */
    private String userExpectDate;

    /**
     * 订单完成时间
     */
    private String compOrderDate;
}
