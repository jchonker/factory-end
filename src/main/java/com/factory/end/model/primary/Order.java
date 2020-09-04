package com.factory.end.model.primary;

import com.factory.end.util.validate.OrderValidationGroups;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author jchonker
 * @Date 2020/8/26 13:50
 * @Version 1.0
 */
@Data
@Table(name = "pro_Order")
@Entity
public class Order {
    @NotNull(groups = OrderValidationGroups.Update.class, message = "id值不能为空")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 产品编号
     */
    @NotNull(groups = OrderValidationGroups.Update.class,message = "订单号不能为空")
    @Column(name = "Order_No")
    private String orderNo;

    /**
     * 产品名
     */
    @NotNull(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class}, message = "产品名不能为空")
    @NotBlank(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "产品名不能为空")
    @Column(name = "Lot_Name")
    private String lotName;

    /**
     * 产品型号
     */
    @NotNull(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "产品型号不能为空")
    @NotBlank(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "产品型号不能为空")
    @Column(name = "Kind_Name")
    private String kindName;

    /**
     * 产品类型
     */
    @NotNull(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "产品类型不能为空")
    @NotBlank(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "产品类型不能为空")
    @Column(name = "Kind_Class")
    private String kindClass;

    /**
     * 计划值
     */
    @NotNull(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "计划值不能为空")
    @Column(name = "Target_Value")
    private Integer targetValue;

    /**
     * 系统预期完成时间
     */
    @Column(name = "Comp_Expect_Date")
    private String compExpectDate;

    /**
     * 下单人员名
     */
    @NotNull(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "下单人员名不能为空")
    @NotBlank(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "下单人员名不能为空")
    @Column(name = "User_Name")
    private String userName;

    /**
     * 提交订单时间
     */
    @NotNull(groups = OrderValidationGroups.Insert.class,message = "订单提交时间不能为空")
    @NotBlank(groups = OrderValidationGroups.Insert.class,message = "订单提交时间不能为空")
    @Column(name = "Order_Place_Date")
    private String orderPlaceDate;

    /**
     * 订单状态
     * 1，未确认
     * 2，确认
     * 3，排单中
     * 4，回退
     * 5，待生产
     * 6，生产中
     * 7，生产完成
     */
    @NotNull(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "订单状态不能为空")
    @Column(name = "Order_Status")
    private Integer orderStatus;

    /**
     * 更新日期时间
     */
    @NotNull(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "更新日期时间不能为空")
    @NotBlank(groups = {OrderValidationGroups.Insert.class,OrderValidationGroups.Update.class},message = "更新日期时间不能为空")
    @Column(name = "Last_Update")
    private String lastUpdate;

    /**
     * 用户期望完成时间
     */
    @Column(name = "User_Expect_Date")
    private String userExpectDate;

    /**
     * 订单完成时间
     */
    @Column(name = "Comp_Order_Date")
    private String compOrderDate;
}
