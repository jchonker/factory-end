package com.factory.end.model.primary;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author jchonker
 * @Date 2020/8/26 14:06
 * @Version 1.0
 * 生产中标
 */
@Data
@Table(name = "pro_Product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 生产单号
     */
    @Column(name = "Product_No")
    @NotNull(message = "生产单号不能为空")
    private Integer productNo;

    /**
     * 订单编号
     */
    @Column(name = "Order_No")
    @NotNull(message = "订单编号不能为空")
    private Integer orderNo;

    /**
     * 设备编号
     */
    @Column(name = "Equipment_No")
    @NotNull(message = "设备编号不能为空")
    private Integer equipmentNo;

    /**
     * 计划值
     */
    @Column(name = "Target_Value")
    @NotNull(message = "计划值不能为空")
    private Integer targetValue;

    /**
     * 生产开始时间
     */
    @Column(name = "Start_Date")
    @NotNull(message = "生产开始时间不能为空")
    private String startDate;

    /**
     * 实际值(默认为0)
     */
    @Column(name = "Collect_Value")
    private Integer collectValue;

    /**
     * 预期完成时间
     */
    @Column(name = "Comp_Expect_Date")
    private String compExpectDate;

    /**
     * 生产完成标志  写入“1”(待生产)或“2”(生产中)或"3"(生产完成)
     */
    @Column(name = "Comp_Product_Flg")
    private Integer compProductFlg;

    /**
     * 生产完成进度
     */
    @Column(name = "Comp_Product_Progress")
    private String compProductProgress;

    /**
     * 更新日期时间
     */
    @Column(name = "Last_Update")
    private String lastUpdate;

    /**
     * 正品值
     */
    @Column(name = "OK_Value")
    private Integer okValue;

    /**
     * 次品值
     */
    @Column(name = "NG_Value")
    private Integer ngValue;

    /**
     * 生产完成时间
     */
    @Column(name = "Comp_Product_Date")
    private String compProductDate;
}
