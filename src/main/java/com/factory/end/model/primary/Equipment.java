package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author jchonker
 * @Date 2020/8/26 13:34
 * @Version 1.0
 */
@Data
@Table(name = "pro_Equipment")
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Equipment_No")
    private String equipmentNo;

    @Column(name = "Equipment_Name")
    private String equipmentName;

    @Column(name = "Production_type")
    private String productionType;

    @Column(name = "Equipment_Status")
    private Integer equipmentStatus;

    @Column(name = "Startup_Date")
    private String startupDate;

    @Column(name = "Proess_Date")
    private String proessDate;

    @Column(name = "Allow_Product")
    private Integer allowProduct;

    @Column(name = "Auto_Model")
    private Integer autoModel;

    @Column(name = "Production_Sequence")
    private Integer productionSequence;

    @Column(name = "Production_Number")
    private Integer productionNumber;
}
