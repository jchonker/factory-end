package com.factory.end.controller.primary;

import com.factory.end.model.primary.Scheduling;
import com.factory.end.service.primary.SchedulingService;
import com.factory.end.util.http.Result;
import com.factory.end.util.validate.SchedulingValidationGroups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private Logger logger = LoggerFactory.getLogger(SchedulingController.class);

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
        logger.info("scheduling/findAll");
        List<Scheduling> schedulings = schedulingService.findAll();
        return result.Success(schedulings);
    }

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    @GetMapping("/findAllByOrderNo/{orderNo}")
    @ApiOperation("根据订单号查询")
    public Result findAllByOrderNo(@PathVariable("orderNo") String orderNo) {
        logger.info("scheduling/findAllByOrderNo");
        logger.info("参数 orderNo:"+orderNo);
        Scheduling scheduling = schedulingService.findByOrderNo(orderNo);
        return result.Success(scheduling);
    }

    /**
     * 根据设备号查询所有数据
     * @param equNo
     * @return
     */
    @GetMapping("/findAllByEquipmentNo/{equNo}")
    @ApiOperation("根据设备号查询所有数据")
    public Result findAllByEquipmentNo(@PathVariable("equNo") String equNo) {
        logger.info("scheduling/findAllByEquipmentNo");
        logger.info("参数 equNo:"+equNo);
        List<Scheduling> allByEquipmentNo = schedulingService.findAllByEquipmentNo(equNo);
        return result.Success(allByEquipmentNo);
    }

    /**
     * 根据产品型号查询
     * @param kindName
     * @return
     */
    @GetMapping("/findAllByKindName/{kindName}")
    @ApiOperation("根据产品型号查询")
    public Result findAllByKindName(@PathVariable("kindName") String kindName) {
        logger.info("scheduling/findAllByKindName");
        logger.info("参数 kindName:"+kindName);
        List<Scheduling> allByKindName = schedulingService.findAllByKindName(kindName);
        return result.Success(allByKindName);
    }

    /**
     * 根据产品类型查询
     * @param kindClass
     * @return
     */
    @GetMapping("/findAllByKindClass/{kindClass}")
    @ApiOperation("根据产品类型查询")
    public Result findAllByKindClass(@PathVariable("kindClass") String kindClass) {
        logger.info("scheduling/findAllByKindClass");
        logger.info("参数 kindClass:"+kindClass);
        List<Scheduling> allByKindClass = schedulingService.findAllByKindClass(kindClass);
        return result.Success(allByKindClass);
    }

    /**
     * 根据产品名称查询
     * @param lotName
     * @return
     */
    @GetMapping("/findAllByLotName/{lotName}")
    @ApiOperation("根据产品名称查询")
    public Result findAllByLotName(@PathVariable("lotName") String lotName) {
        logger.info("scheduling/findAllByLotName");
        logger.info("参数 lotName:"+lotName);
        List<Scheduling> allByLotName = schedulingService.findAllByLotName(lotName);
        return result.Success(allByLotName);
    }

    /**
     * 根据下单人员名查询
     * @param userName
     * @return
     */
    @GetMapping("/findAllByUserName/{userName}")
    @ApiOperation("根据下单人员名查询")
    public Result findAllByUserName(@PathVariable("userName") String userName) {
        logger.info("scheduling/findAllByUserName");
        logger.info("参数 username:"+userName);
        List<Scheduling> allByUserName = schedulingService.findAllByUserName(userName);
        return result.Success(allByUserName);
    }

    /**
     * 根据下单时间段查询
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/findAllByOrderPlaceDateBetween/{startTime}/{endTime}")
    @ApiOperation("根据下单时间段查询")
    public Result findAllByOrderPlaceDateBetween(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime) {
        logger.info("scheduling/findAllByOrderPlaceDateBetween");
        logger.info("参数 startTime:"+startTime+" endTime:"+endTime);
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
        logger.info("scheduling/findAllByManuOrder");
        logger.info("参数 manuOrder:"+manuOrder);
        List<Scheduling> allByManuOrder = schedulingService.findAllByManuOrder(manuOrder);
        return result.Success(allByManuOrder);
    }

    /**
     * 根据订单号删除订单
     * @param orderNo
     * @return
     */
    @DeleteMapping("/deleteByOrderNo/{orderNo}")
    @ApiOperation("根据订单号删除订单")
    public Result deleteByOrderNo(@PathVariable("orderNo") String orderNo) {
        logger.info("scheduling/deleteByOrderNo");
        logger.info("参数 orderNo:"+orderNo);
        if(schedulingService.existsByOrderNo(orderNo)){
            Integer delete = schedulingService.deleteByOrderNo(orderNo);
            if(delete != 0){
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
    @DeleteMapping("/deleteByUserName/{username}")
    @ApiOperation("根据下单人员删除")
    public Result deleteByUserName(@PathVariable("username") String username) {
        logger.info("scheduling/deleteByUserName");
        logger.info("参数 username:"+username);
        if(schedulingService.existsByUserName(username)){
            Integer delete = schedulingService.deleteByUserName(username);
            if(delete != 0){
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
    @GetMapping("/existsByOrderNo/{orderNo}")
    @ApiOperation("根据订单号查询订单是否存在")
    public Result existsByOrderNo(@PathVariable("orderNo") String orderNo) {
        logger.info("scheduling/existsByOrderNo");
        logger.info("参数 orderNo:"+orderNo);
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
    public Result saveScheduking(@Validated(SchedulingValidationGroups.Insert.class) @RequestBody Scheduling scheduling){
        logger.info("scheduling/saveScheduling");
        logger.info("参数 scheduling:"+scheduling);
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
    public Result saveSchedukings(@Validated(SchedulingValidationGroups.Insert.class) @RequestBody List<Scheduling> schedulings){
        logger.info("scheduling/saveSchedulings");
        logger.info("参数 saveSchedulings:"+schedulings);
        schedulingService.saveSchedulings(schedulings);
        return result.Success();
    }

    /**
     * 单条数据修改
     * 带入id值就是修改
     * @param scheduling
     * @return
     */
    @PutMapping("/updateScheduling")
    @ApiOperation("修改单条数据")
    public Result updateScheduling(@Validated(SchedulingValidationGroups.Update.class) @RequestBody Scheduling scheduling){
        logger.info("scheduling/updateScheduling");
        logger.info("参数 scheduling:"+scheduling);
        schedulingService.saveScheduling(scheduling);
        return result.Success();
    }

    /**
     * 批量修改数据
     * @param schedulingList
     * @return
     */
    @PutMapping("/updateSchedulings")
    @ApiOperation("批量修改数据")
    public Result updateSchedulings(@Validated(SchedulingValidationGroups.Update.class) @RequestBody List<Scheduling> schedulingList){
        logger.info("scheduling/updateSchedulings");
        logger.info("参数 schedulingList:"+schedulingList);
        schedulingService.saveSchedulings(schedulingList);
        return result.Success();
    }

    @GetMapping("/findByPage/{currentPage}/{pageSize}")
    @ApiOperation("多条件分页查询")
    public Result findByPage(@ModelAttribute Scheduling scheduling,@PathVariable("currentPage") Integer currentPage,@PathVariable("pageSize") Integer pageSize){
        logger.info("order/findByPage");
        logger.info("多条件分页查询:");
        logger.info("scheduling:"+scheduling);
        logger.info("currentPage:"+currentPage+" pageSize:"+pageSize);
        //同步前端传回的当前页参数
        Integer currentPageReal = currentPage - 1;
        Page<Scheduling> byPage = schedulingService.findByPage(scheduling, currentPage, pageSize);
        return result.Success(byPage);
    }
}
