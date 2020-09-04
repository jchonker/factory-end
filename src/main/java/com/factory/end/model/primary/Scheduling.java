package com.factory.end.model.primary;

import com.factory.end.util.validate.SchedulingValidationGroups;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author jchonker
 * @Date 2020/8/26 16:51
 * @Version 1.0
 */
@Data
@Table(name = "pro_Scheduling")
@Entity
public class Scheduling {
    @NotNull(groups = SchedulingValidationGroups.Update.class,message = "ID值不能为空")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "Order_No")
    @NotNull(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "订单编号不能为空")
    @NotBlank(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "订单编号不能为空")
    private String orderNo;

    /**
     * 设备号
     */
    @Column(name = "Equipment_No")
    @NotNull(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "设备号不能为空")
    private String equipmentNo;

    /**
     * 产品名
     */
    @Column(name = "Lot_Name")
    @NotNull(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "产品名不能为空")
    @NotBlank(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "产品名不能为空")
    private String lotName;

    /**
     * 产品型号
     */
    @Column(name = "kind_Name")
    @NotNull(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "产品型号不能为空")
    @NotBlank(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "产品型号不能为空")
    private String kindName;

    /**
     * 产品类型
     */
    @Column(name = "kind_Class")
    @NotNull(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "产品型号不能为空")
    @NotBlank(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "产品型号不能为空")
    private String kindClass;

    /**
     * 计划值
     */
    @Column(name = "Target_Value")
    @NotNull(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "计划值不能为空")
    private Integer targetValue;

    /**
     * 下单人员名
     */
    @Column(name = "User_Name")
    @NotNull(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "下单人员名不能为空")
    private String userName;

    /**
     * 提交订单时间
     */
    @Column(name = "Order_Place_Date")
    @NotNull(groups = {SchedulingValidationGroups.Insert.class,SchedulingValidationGroups.Update.class},message = "提交订单时间不能为空")
    private String orderPlaceDate;

    /**
     * 预期完成时间
     */
    @Column(name = "Comp_Expect_Date")
    private String compExpectDate;

    /**
     * 生产顺序(生产的优先顺序)
     */
    @Column(name = "Manu_Order")
    private Integer manuOrder;

    /**
     * 更新日期时间
     */
    @Column(name = "Last_Update")
    private String lastUpdate;

    /**
     * 订单状态
     */
    @Column(name = "Order_Status")
    private Integer orderStatus;
}
