package com.factory.end.controller.primary;

import com.alibaba.fastjson.JSON;
import com.factory.end.model.primary.Scheduling;
import com.factory.end.service.primary.SchedulingService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 17:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/scheduling")
@Api(description = "排单完成表")
public class SchedulingController {
    @Autowired
    private SchedulingService schedulingService;

    @Autowired
    Result result;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/findAll")
    @ApiOperation("查询所有数据")
    public Result findAll(){
        List<Scheduling> schedulings = schedulingService.findAll();
        return result.Success(schedulings);
    }

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    @GetMapping("/findAllByOrderNo")
    @ApiOperation("根据订单号查询")
    public Result findAllByOrderNo(Integer orderNo) {
        List<Scheduling> allByOrderNo = schedulingService.findAllByOrderNo(orderNo);
        return result.Success(allByOrderNo);
    }

    /**
     * 根据设备号查询所有数据
     * @param equNo
     * @return
     */
    @GetMapping("/findAllByEquipmentNo")
    @ApiOperation("根据设备号查询所有数据")
    public Result findAllByEquipmentNo(Integer equNo) {
        List<Scheduling> allByEquipmentNo = schedulingService.findAllByEquipmentNo(equNo);
        return result.Success(allByEquipmentNo);
    }

    /**
     * 根据产品型号查询
     * @param kindName
     * @return
     */
    @GetMapping("/findAllByKindName")
    @ApiOperation("根据产品型号查询")
    public Result findAllByKindName(String kindName) {
        List<Scheduling> allByKindName = schedulingService.findAllByKindName(kindName);
        return result.Success(allByKindName);
    }

    /**
     * 根据产品类型查询
     * @param kindClass
     * @return
     */
    @GetMapping("/findAllByKindClass")
    @ApiOperation("根据产品类型查询")
    public Result findAllByKindClass(String kindClass) {
        List<Scheduling> allByKindClass = schedulingService.findAllByKindClass(kindClass);
        return result.Success(allByKindClass);
    }

    /**
     * 根据产品名称查询
     * @param lotName
     * @return
     */
    @GetMapping("/findAllByLotName")
    @ApiOperation("根据产品名称查询")
    public Result findAllByLotName(String lotName) {
        List<Scheduling> allByLotName = schedulingService.findAllByLotName(lotName);
        return result.Success(allByLotName);
    }

    /**
     * 根据下单人员名查询
     * @param userName
     * @return
     */
    @GetMapping("/findAllByUserName")
    @ApiOperation("根据下单人员名查询")
    public Result findAllByUserName(String userName) {
        List<Scheduling> allByUserName = schedulingService.findAllByUserName(userName);
        return result.Success(allByUserName);
    }

    /**
     * 根据下单时间段查询
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/findAllByOrderPlaceDateBetween")
    @ApiOperation("根据下单时间段查询")
    public Result findAllByOrderPlaceDateBetween(String startTime, String endTime) {
        List<Scheduling> allByOrderPlaceDateBetween = schedulingService.findAllByOrderPlaceDateBetween(startTime, endTime);
        return result.Success(allByOrderPlaceDateBetween);
    }

    /**
     * 根据生产顺序查询
     * @param manuOrder
     * @return
     */
    @GetMapping("/findAllByManuOrder")
    @ApiOperation("根据生产顺序查询")
    public Result findAllByManuOrder(Integer manuOrder) {
        List<Scheduling> allByManuOrder = schedulingService.findAllByManuOrder(manuOrder);
        return result.Success(allByManuOrder);
    }

    /**
     * 根据订单号删除订单
     * @param orderNo
     * @return
     */
    @DeleteMapping("/deleteByOrderNo")
    @ApiOperation("根据订单号删除订单")
    public Result deleteByOrderNo(Integer orderNo) {
        if(schedulingService.existsByOrderNo(orderNo)){
            boolean delete = schedulingService.deleteByOrderNo(orderNo);
            if(delete){
                return result.Success();
            }
            return result.Fail();
        }else {
            return result.Fail("订单不存在!");
        }
    }

    /**
     * 根据下单人员删除
     * @param username
     * @return
     */
    @DeleteMapping("/deleteByUserName")
    @ApiOperation("根据下单人员删除")
    public Result deleteByUserName(String username) {
        if(schedulingService.existsByUserName(username)){
            boolean delete = schedulingService.deleteByUserName(username);
            if(delete){
                return result.Success();
            }
            return result.Fail();
        }
        else {
            return result.Fail("订单不存在!");
        }
    }

    /**
     * 根据订单号查询订单是否存在
     * @param orderNo
     * @return
     */
    @GetMapping("/existsByOrderNo")
    @ApiOperation("根据订单号查询订单是否存在")
    public Result existsByOrderNo(Integer orderNo) {
        boolean exists = schedulingService.existsByOrderNo(orderNo);
        if(exists){
            return result.Success(true);
        }
        return result.Success(false);
    }

    /**
     * 插入单条数据
     * @param scheduling
     * @return
     */
    @PostMapping("/saveScheduling")
    @ApiOperation("插入单条数据")
    public Result saveScheduking(@Validated @RequestBody Scheduling scheduling){
        schedulingService.saveScheduling(scheduling);
        return result.Success();
    }

    /**
     * 批量插入
     * @param schedulings
     * @return
     */
    @PostMapping("/saveSchedulings")
    @ApiOperation("批量插入")
    public Result saveSchedukings(@Validated @RequestBody List<Scheduling> schedulings){
        schedulingService.saveSchedulings(schedulings);
        return result.Success();
    }


}
