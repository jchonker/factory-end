package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "Order_No")
    @NotNull(message = "订单编号不能为空")
    private Integer orderNo;

    /**
     * 设备号
     */
    @Column(name = "Equipment_No")
    @NotNull(message = "设备号不能为空")
    private Integer equipmentNo;

    /**
     * 产品名
     */
    @Column(name = "Lot_Name")
    @NotNull(message = "产品名不能为空")
    private String lotName;

    /**
     * 产品型号
     */
    @Column(name = "kind_Name")
    @NotNull(message = "产品型号不能为空")
    private String kindName;

    /**
     * 产品类型
     */
    @Column(name = "kind_Class")
    @NotNull(message = "产品型号不能为空")
    private String kindClass;

    /**
     * 计划值
     */
    @Column(name = "Target_Value")
    @NotNull(message = "计划值不能为空")
    private Integer targetValue;

    /**
     * 下单人员名
     */
    @Column(name = "Username")
    @NotNull(message = "下单人员名不能为空")
    private String userName;

    /**
     * 提交订单时间
     */
    @Column(name = "Order_Place_Date")
    @NotNull(message = "提交订单时间不能为空")
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
}
