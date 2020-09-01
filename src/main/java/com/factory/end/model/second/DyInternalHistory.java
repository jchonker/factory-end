package com.factory.end.model.second;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author jchonker
 * @Date 2020/8/13 16:24
 * @Version 1.0
 * 模板类
 */
@Data
@Table(name = "DY_Internal_History")
@Entity
public class DyInternalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private Integer Product_ID;

    private String Lot_Name;

    private String Barcode;

    private Integer Print_Flag;

    private Date Production_Date;

    private String Product_Name;

    private Date Print_Date;

    private Integer Lot_ID;
}
