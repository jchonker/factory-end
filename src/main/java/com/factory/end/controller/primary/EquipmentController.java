package com.factory.end.controller.primary;

import com.factory.end.dto.primary.EquipmentDto;
import com.factory.end.model.primary.Equipment;
import com.factory.end.service.primary.EquipmentService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 15:01
 * @Version 1.0
 */
@Controller
@RequestMapping("/equipment")
@Api(description = "设备")
public class EquipmentController {
    private Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    Result result;

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("查询所有设备信息")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "失败")})
    public Result findAll(){
        logger.info("equipment/findAll");
        List<Equipment> equipmentServiceAll = equipmentService.findAll();
        return result.Success(equipmentServiceAll);
    }
}
