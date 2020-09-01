package com.factory.end.model.primary;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author jchonker
 * @Date 2020/8/26 13:40
 * @Version 1.0
 */
@Data
@Table(name = "pro_Equipment_Workers")
@Entity
public class EquipmentWorkers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Equipment_No")
    private Integer equipmentNo;

    @Column(name = "Workers_No")
    private String workersNo;

    @Column(name = "Workers_Date")
    private String workersDate;
}
