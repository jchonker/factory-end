package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author jchonker
 * @Date 2020/8/29 17:42
 * @Version 1.0
 * 生产完成表
 */
@Data
@Table(name = "pro_Product_history")
@Entity
public class ProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 生产单号
     */
    @Column(name = "Product_No")
    private String productNo;

    /**
     * 订单编号
     */
    @Column(name = "Order_No")
    private String orderNo;

    /**
     * 设备编号
     */
    @Column(name = "Equipment_No")
    private String equipmentNo;

    /**
     * 计划值
     */
    @Column(name = "Target_Value")
    private Integer targetValue;

    /**
     * 生产开始时间
     */
    @Column(name = "Start_Date")
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
     * 生产完成标志
     */
    @Column(name = "Order_Status")
    private Integer orderStatus;

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
     * 下单人员名
     */
    @Column(name = "User_Name")
    private String userName;

    /**
     * 生产完成时间
     */
    @Column(name = "Comp_Product_Date")
    private String compProductDate;

}
