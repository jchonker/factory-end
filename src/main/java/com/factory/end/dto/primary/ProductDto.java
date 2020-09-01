package com.factory.end.dto.primary;

import lombok.Data;

/**
 * @Author jchonker
 * @Date 2020/8/26 13:57
 * @Version 1.0
 * 正在生产的表
 */
@Data
public class ProductDto {
    private Integer id;

    /**
     * 生产单号
     */
    private Integer productNo;

    /**
     * 订单编号
     */
    private Integer orderNo;

    /**
     * 设备编号
     */
    private Integer equipmentNo;

    /**
     * 计划值
     */
    private Integer targetValue;

    /**
     * 生产开始时间
     */
    private String startDate;

    /**
     * 实际值(默认为0)
     */
    private Integer collectValue;

    /**
     * 预期完成时间
     */
    private String compExpectDate;

    /**
     * 生产完成标志  写入“1”(待生产)或“2”(生产中)或"3"(生产完成)
     */
    private Integer compProductFlg;

    /**
     * 生产完成进度
     */
    private String compProductProgress;

    /**
     * 更新日期时间
     */
    private String lastUpdate;

    /**
     * 正品值
     */
    private Integer okValue;

    /**
     * 次品值
     */
    private Integer ngValue;

    /**
     * 生产完成时间
     */
    private String compProductDate;

}
