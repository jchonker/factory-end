package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Author jchonker
 * @Date 2020/9/15 11:16
 * @Version 1.0
 */
@Data
@Table(name = "pro_Project_Details")
@Entity
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 所属公司
     */
    @Column(name = "Company")
    private String company;

    /**
     *项目编号
     */
    @Column(name = "Project_No")
    private String projectNo;

    /**
     *项目名称
     */
    @Column(name = "Project_Name")
    private String projectName;

    /**
     *客户名称
     */
    @Column(name = "Customer_Name")
    private String customerName;

    /**
     *申请日期
     */
    @Column(name = "Apply_Date")
    private String applyDate;

    /**
     *经办人
     */
    @Column(name = "Handler")
    private String handler;

    /**
     *要求交货日期
     */
    @Column(name = "Request_Delivery_Date")
    private String requestDeliveryDate;

    /**
     *发货仓库
     */
    @Column(name = "Warehouse")
    private String warehouse;

    /**
     *合计金额
     */
    @Column(name = "Total")
    private BigDecimal total;

    /**
     *付款类型
     */
    @Column(name = "Pay_Type")
    private String payType;

    /**
     *附加说明
     */
    @Column(name = "Notes")
    private String notes;

    /**
     *发票类型
     */
    @Column(name = "Receipt_Type")
    private String receiptType;

    /**
     *制单人
     */
    @Column(name = "Create_Name")
    private String createName;

    /**
     *制单时间
     */
    @Column(name = "Create_Date")
    private String createDate;

    /**
     *单据状态
     */
    @Column(name = "Bill_Status")
    private String billStatus;

    /**
     *审核人
     */
    @Column(name = "Checker_Name")
    private String checkerName;

    /**
     *审核时间
     */
    @Column(name = "Checker_Date")
    private String checkerDate;

    /**
     *审核意见
     */
    @Column(name = "Checker_Notes")
    private String checkerNotes;

    /**
     * 项目状态
     */
    @Column(name = "Project_Status")
    private Integer projectStatus;
}
