package com.factory.end.model.primary;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/21 15:37
 * @Version 1.0
 * 模板类
 */
@Data
@Table(name = "pro_User")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private boolean enable;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    private boolean accountNonExpired;

    @ManyToMany
    @JoinTable(name = "pro_User_Role",uniqueConstraints = {@UniqueConstraint(columnNames = {"uid","rid"})},
            joinColumns = {@JoinColumn(name = "uid",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "rid",referencedColumnName = "id")})
    private List<Role> roleList = new ArrayList<>();

}
