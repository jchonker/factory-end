package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:24
 * @Version 1.0
 */
@Data
@Table(name = "pro_Status")
@Entity
public class Workers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /**
     * 职工No
     * 使用String类型作主键
     */
    @Column(name = "Workers_No")
    private String Workers_No;

    /**
     * 职工名
     */
    @Column(name = "Workers_Name")
    private String Workers_Name;
}
