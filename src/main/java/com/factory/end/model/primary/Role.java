package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author jchonker
 * @Date 2020/10/22 17:03
 * @Version 1.0
 * 角色
 */
@Data
@Table(name = "pro_Role")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "nameZh")
    private String nameZh;
}
