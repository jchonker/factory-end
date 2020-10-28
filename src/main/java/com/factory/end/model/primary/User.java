package com.factory.end.model.primary;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    private String username;

    private String password;

    private String roles;

    private boolean enable;

}
