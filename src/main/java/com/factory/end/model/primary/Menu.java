package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/10/22 17:09
 * @Version 1.0
 * 资源表
 */
//@Data
//@Table(name = "pro_Menu")
//@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pattern")
    private String pattern;

    
    private List<Role> roles;
}
