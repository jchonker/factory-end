package com.factory.end.dto.primary;

import lombok.Data;


/**
 * @Author jchonker
 * @Date 2020/8/26 17:10
 * @Version 1.0
 */
@Data
public class StatusDto{
    private Integer id;

    /**
     * 设备No
     */
    private Integer equipmentNo;

    /**
     * 产品型号
     */
    private String kindName;

    /**
     * 产品类别
     */
    private String kindClass;

    /**
     * 产品名称
     */
    private String lotName;

    /**
     * 实际值
     */
    private Integer collectValue;

    /**
     * 正品数
     */
    private Integer okValue;

    /**
     * 次品数
     */
    private Integer ngValue;

    /**
     * 计划值
     */
    private Integer targetValue;

    /**
     * 生产完成标志  写入“1”(暂时未完成)或“2”(完成)
     */
    private Integer compProductFlg;

    /**
     * 更新日期时间
     */
    private String lastUpdate;
}
