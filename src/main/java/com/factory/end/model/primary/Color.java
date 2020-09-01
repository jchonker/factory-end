package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author jchonker
 * @Date 2020/8/28 10:22
 * @Version 1.0
 */
@Data
@Table(name = "pro_Color")
@Entity
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Equipment_No")
    private Integer equipmentNo;

    @Column(name = "Back_Color")
    private String backColor;

    @Column(name = "Back_Color_R")
    private Integer backColor_R;

    @Column(name = "Back_Color_G")
    private Integer backColorG;

    @Column(name = "Back_Color_B")
    private Integer backColorB;

    @Column(name = "Font_Color")
    private String fontColor;

    @Column(name = "Font_Color_R")
    private Integer fontColorR;

    @Column(name = "Font_Color_G")
    private Integer fontColorG;

    @Column(name = "Font_Color_B")
    private Integer fontColorB;

    @Column(name = "Last_Update")
    private String lastUpdate;
}
