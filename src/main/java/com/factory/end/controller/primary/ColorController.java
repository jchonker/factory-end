package com.factory.end.controller.primary;

import com.factory.end.model.primary.Color;
import com.factory.end.service.primary.ColorService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 13:29
 * @Version 1.0
 */
@RestController
@RequestMapping("/color")
@Api(description = "设备对应的颜色")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @Autowired
    Result result;

    @GetMapping("/findColorByEquipmentNo/{equNo}")
    @ApiOperation("根据设备名查询颜色")
    public Result findColorByEquipmentNo(@PathVariable("equNo") Integer equNo){
        Color colorByEquipmentNo = colorService.findColorByEquipmentNo(equNo);
        if(colorByEquipmentNo != null){
            return result.Success(colorByEquipmentNo);
        }
        return result.Success();
    }

    @GetMapping("/findAllColor")
    @ApiOperation("根据所有设备颜色")
    public Result findAllColor(){
        List<Color> allColor = colorService.findAllColor();
        if(allColor != null){
            return result.Success(allColor);
        }
        return result.Success();
    }
}
