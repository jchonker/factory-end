package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:18
 * @Version 1.0
 */
@Data
@Table(name = "pro_Status")
@Entity
public class Status{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 设备No
     */
    @Column(name = "Equipment_No")
    private Integer equipmentNo;

    /**
     * 产品型号
     */
    @Column(name = "Kind_Name")
    private String kindName;

    /**
     * 产品类别
     */
    @Column(name = "Kind_Class")
    private String kindClass;

    /**
     * 产品名称
     */
    @Column(name = "Lot_Name")
    private String lotName;

    /**
     * 实际值
     */
    @Column(name = "Collect_Value")
    private Integer collectValue;

    /**
     * 正品数
     */
    @Column(name = "OK_Value")
    private Integer okValue;

    /**
     * 次品数
     */
    @Column(name = "NG_Value")
    private Integer ngValue;

    /**
     * 计划值
     */
    @Column(name = "Target_Value")
    private Integer targetValue;

    /**
     * 生产完成标志  写入“1”(暂时未完成)或“2”(完成)
     */
    @Column(name = "Comp_Product_Flg")
    private Integer compProductFlg;

    /**
     * 更新日期时间
     */
    @Column(name = "Last_Update")
    private String lastUpdate;
}
