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
    private Integer equipmentNo;

    @Column(name = "Equipment_Name")
    private String equipmentName;

    @Column(name = "Production_type")
    private String productionType;
}
