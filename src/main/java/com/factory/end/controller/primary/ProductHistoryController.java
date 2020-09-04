package com.factory.end.controller.primary;

import com.factory.end.model.primary.ProductHistory;
import com.factory.end.service.primary.ProductHistoryService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    private Logger logger = LoggerFactory.getLogger(ProductHistoryController.class);

    @Autowired
    private ProductHistoryService productHistoryService;

    @Autowired
    Result result;

    @GetMapping("/findAll")
    @ApiOperation("查询所有记录")
    public Result findAll(){
        logger.info("productHistory/logger");
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
        logger.info("productHistory/findAllByOrderNo");
        logger.info("参数 orderNo:"+orderNo);
        List<ProductHistory> allByOrderNo = productHistoryService.findAllByOrderNo(orderNo);
        if(allByOrderNo != null){
            return result.Success(allByOrderNo);
        }else {
            return result.Fail("查询无值");
        }
    }

    @GetMapping("/findAllByEquipmentNo/{equNo}")
    @ApiOperation("根据设备号查询")
    public Result findAllByEquipmentNo(@PathVariable("equNo") String equNo){
        logger.info("productHistory/findAllByEquipmentNo");
        logger.info("参数 equNo:"+equNo);
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
        logger.info("productHistory/findAllByCompleteTimeBetween");
        logger.info("参数 startTime:"+startTime+" endTime:"+endTime);
        List<ProductHistory> allByCompleteTimeBetween = productHistoryService.findAllByCompProductDateBetween(startTime, endTime);
        if(allByCompleteTimeBetween != null){
            return result.Success(allByCompleteTimeBetween);
        }else {
            return result.Fail("查询无值");
        }
    }

    @GetMapping("/findByPage/{currentPage}/{pageSize}")
    @ApiOperation("多条件分页查询")
    public Result findByPage(@ModelAttribute ProductHistory productHistory, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize){
        logger.info("product/findByPage");
        logger.info("多条件分页查询:");
        logger.info("productHistory:"+productHistory);
        logger.info("currentPage:"+currentPage+" pageSize:"+pageSize);
        //同步前端传回的当前页参数
        Integer currentPageReal = currentPage - 1;
        Page<ProductHistory> byPage = productHistoryService.findByPage(productHistory, currentPageReal, pageSize);
        return result.Success(byPage);
    }
}
