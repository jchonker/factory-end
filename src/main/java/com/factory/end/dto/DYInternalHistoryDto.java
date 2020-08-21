package com.factory.end.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author jchonker
 * @Date 2020/8/13 9:49
 * @Version 1.0
 */
@Data
public class DYInternalHistoryDto {
    private int ID;

    private int Product_ID;

    private String Lot_Name;

    private String Barcode;

    private int Print_Flag;

    private Date Production_Date;

    private String Product_Name;

    private Date Print_Date;

    private int Lot_ID;
}
