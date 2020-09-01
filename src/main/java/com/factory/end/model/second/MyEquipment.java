package com.factory.end.model.second;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author jchonker
 * @Date 2020/8/14 9:35
 * @Version 1.0
 * 模板类
 */
@Data
@Table(name = "equipment")
@Entity
public class MyEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "equ_number")
    private String equNumber;

    @Column(name = "varieties_key")
    private String varietiesKey;

    @Column(name = "varieties_value")
    private String varietiesValue;

    @Column(name = "real_key")
    private String realKey;

    @Column(name = "real_value")
    private Integer realValue;

    @Column(name = "current_key")
    private String currentKey;

    @Column(name = "current_value")
    private Integer currentValue;

    @Column(name = "bgcolor")
    private String bgcolor;

    @Column(name = "equ_number_color")
    private String equNumberColor;

    @Column(name = "varieties_key_color")
    private String varietiesKeyColor;

    @Column(name = "varieties_value_color")
    private String varietiesValueColor;

    @Column(name = "real_key_color")
    private String realKeyColor;

    @Column(name = "real_value_color")
    private String realValueColor;

    @Column(name = "current_key_color")
    private String currentKeyColor;

    @Column(name = "current_value_color")
    private String currentValueColor;
}
