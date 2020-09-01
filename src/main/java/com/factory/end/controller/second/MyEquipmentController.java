package com.factory.end.controller.second;

import com.factory.end.dto.second.MyEquipmentDto;
import com.factory.end.service.second.MyEquipmentService;
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
@RequestMapping(value = "/myequipment")
//@Api(description = "自定义设备")
public class MyEquipmentController {
    @Autowired
    private MyEquipmentService myEquipmentService;

    @GetMapping(value = "/findAll")
//    @ApiOperation(value = "查询所有的设备数据")
//    @ApiResponses(value = {@ApiResponse(code = 200,message = "成功"),
//                @ApiResponse(code = 400,message = "失败")})
    public List<MyEquipmentDto> findAll(){
        List<MyEquipmentDto> myEquipmentDtoList = myEquipmentService.findAll();
        return myEquipmentDtoList;
    }
}
