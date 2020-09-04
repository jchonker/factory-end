package com.factory.end.model.primary;

import com.factory.end.util.validate.ProductValidationGroups;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotNull(groups = ProductValidationGroups.Update.class,message = "ID值不能为空")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 生产单号
     */
    @Column(name = "Product_No")
//    @NotNull(groups = {ProductValidationGroups.Insert.class,ProductValidationGroups.Update.class},message = "生产单号不能为空")
//    @NotBlank(groups = {ProductValidationGroups.Insert.class,ProductValidationGroups.Update.class},message = "生产单号不能为空")
    private String productNo;

    /**
     * 订单编号
     */
    @Column(name = "Order_No")
//    @NotNull(groups = {ProductValidationGroups.Insert.class,ProductValidationGroups.Update.class},message = "订单编号不能为空")
//    @NotBlank(groups = {ProductValidationGroups.Insert.class,ProductValidationGroups.Update.class},message = "订单编号不能存在")
    private String orderNo;

    /**
     * 设备编号
     */
    @Column(name = "Equipment_No")
//    @NotNull(groups = {ProductValidationGroups.Insert.class,ProductValidationGroups.Update.class},message = "设备编号不能为空")
//    @NotBlank(groups = {ProductValidationGroups.Insert.class,ProductValidationGroups.Update.class},message = "设备编号不能为空")
    private String equipmentNo;

    /**
     * 计划值
     */
    @Column(name = "Target_Value")
    //@NotNull(groups = {ProductValidationGroups.Insert.class,ProductValidationGroups.Update.class},message = "计划值不能为空")
    private Integer targetValue;

    /**
     * 生产开始时间
     */
    @Column(name = "Start_Date")
    //@NotNull(groups = {ProductValidationGroups.Insert.class,ProductValidationGroups.Update.class},message = "生产开始时间不能为空")
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
