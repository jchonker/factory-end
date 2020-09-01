package com.factory.end.controller.primary;

import com.factory.end.model.primary.ProductHistory;
import com.factory.end.service.primary.ProductHistoryService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/29 17:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/productHistory")
@Api(value = "/productHistory",description = "生产完成记录")
public class ProductHistoryController {
    @Autowired
    private ProductHistoryService productHistoryService;

    @Autowired
    Result result;

    @GetMapping("/findAll")
    @ApiOperation("查询所有记录")
    public Result findAll(){
        List<ProductHistory> productHistories = productHistoryService.findAll();
        if(productHistories != null){
            return result.Success(productHistories);
        }
        else {
            return result.Fail("查询无值");
        }
    }

    @GetMapping("/findAllByOrderNo/{orderNo}")
    @ApiOperation("根据订单号查询")
    public Result findAllByOrderNo(@PathVariable("orderNo") Integer orderNo){
        List<ProductHistory> allByOrderNo = productHistoryService.findAllByOrderNo(orderNo);
        if(allByOrderNo != null){
            return result.Success(allByOrderNo);
        }else {
            return result.Fail("查询无值");
        }
    }

    @GetMapping("/findAllByEquipmentNo/{equNo}")
    @ApiOperation("根据设备号查询")
    public Result findAllByEquipmentNo(@PathVariable("equNo") Integer equNo){
        List<ProductHistory> allByEquipmentNo = productHistoryService.findAllByEquipmentNo(equNo);
        if(allByEquipmentNo != null){
            return result.Success(allByEquipmentNo);
        }
        else {
            return result.Fail("查询无值");
        }
    }

    @GetMapping("findAllByCompleteTimeBetween/{startTime}/{endTime}")
    @ApiOperation("根据订单完成时间查询")
    public Result findAllByCompleteTimeBetween(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime){
        List<ProductHistory> allByCompleteTimeBetween = productHistoryService.findAllByCompProductDateBetween(startTime, endTime);
        if(allByCompleteTimeBetween != null){
            return result.Success(allByCompleteTimeBetween);
        }else {
            return result.Fail("查询无值");
        }
    }

}
