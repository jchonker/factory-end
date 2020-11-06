package com.factory.end.model.primary;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/10/22 17:03
 * @Version 1.0
 * 角色
 */
//@Data
@Table(name = "pro_Role")
@Entity
public class Role implements Comparable<Role>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_zh")
    private String nameZh;

    /**
     * 使得Role对象放弃外键的维护
     * 此字段不能添加使用getter setter方法
     */
    @ManyToMany(mappedBy = "roleList")
    private List<User> userList = new ArrayList<>();

    /**
     * 自定义排序规则
     * @param o
     * @return
     */
    @Override
    public int compareTo(Role o) {
        return compare(this.id,o.getId());
    }

    public static int compare(Integer id1, Integer id2) {
        return (id1 > id2 ? 1 :
                (id1.equals(id2) ? 0 : -1));
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}
