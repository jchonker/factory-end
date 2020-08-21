package com.factory.end.controller;

import com.factory.end.dao.DYInternalHistoryDao;
import com.factory.end.dto.DYInternalHistoryDto;
import com.factory.end.service.DYInternalHistoryService;
import io.swagger.annotations.*;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/13 10:43
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/in")
@Api(value = "操作内标历史数据的controller")

public class DYInternalHistoryController {

    @Autowired
    DYInternalHistoryService dyInternalHistoryService;

    @ApiOperation(value = "查询所有内标历史数据")
    @GetMapping(value = "/findAll")
    @ApiResponses(value = {@ApiResponse(code = 1000,message = "成功"),
                    @ApiResponse(code = 1001,message = "失败")})
    public List<DYInternalHistoryDto> getAllDYInternalHistory(){
        List<DYInternalHistoryDto> dyInternalHistoryDtos = dyInternalHistoryService.findAll();
        System.out.println("查询有值:");
        for (DYInternalHistoryDto dyInternalHistory:dyInternalHistoryDtos) {
            System.out.println(dyInternalHistory.toString());
        }
        return dyInternalHistoryDtos;
    }

    @ApiOperation(value = "根据id查询内标历史数据")
    @GetMapping(value = "/findById")
    @ApiResponses(value = {@ApiResponse(code = 1000,message = "成功"),
                @ApiResponse(code = 1001,message = "失败"),
                @ApiResponse(code = 1002,message = "缺少参数")})
    public DYInternalHistoryDto getDYInternalHistoryById(@ApiParam("id值") @RequestParam Integer id){
        System.out.println("接收到的参数id:"+id);
        DYInternalHistoryDto dyInternalHistoryById = dyInternalHistoryService.findDYInternalHistoryById(id);
        if(dyInternalHistoryById != null){
            System.out.println("查询到的数据"+dyInternalHistoryById);
        }
        return dyInternalHistoryById;
    }
}
