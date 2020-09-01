package com.factory.end.dto.primary;

import lombok.Data;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:21
 * @Version 1.0
 */
@Data
public class WorkersDto {
    private Integer id;

    /**
     * 职工No
     */
    private String Workers_No;

    /**
     * 职工名
     */
    private String Workers_Name;
}
