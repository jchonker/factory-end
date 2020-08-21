package com.factory.end.controller;

import com.factory.end.dto.EquipmentDto;
import com.factory.end.service.EquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/14 10:15
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/equipment")
@Api(value = "操作设备表的controller")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有的设备数据")
    @ApiResponses(value = {@ApiResponse(code = 1000,message = "成功"),
                @ApiResponse(code = 1001,message = "失败")})
    public List<EquipmentDto> findAll(){
        List<EquipmentDto> equipmentDtoList = equipmentService.findAll();
        return equipmentDtoList;
    }
}
