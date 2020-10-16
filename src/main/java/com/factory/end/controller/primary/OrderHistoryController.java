package com.factory.end.controller.primary;

import com.factory.end.service.primary.OrderHistoryService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author jchonker
 * @Date 2020/10/16 14:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/orderHistory")
@Api(description = "订单历史表")
public class OrderHistoryController {
    private Logger logger = LoggerFactory.getLogger(OrderHistoryController.class);

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    Result result;

    @GetMapping("/findOrderHistoriesGroupByCompOrderDate")
    @ApiOperation("订单完成时间分组查询")
    public Result findOrderHistoriesGroupByCompOrderDate(){
        List<Map<String, Map<String, Map<String, Integer>>>> orderHistoriesGroupByCompOrderDate = orderHistoryService.findOrderHistoriesGroupByCompOrderDate();
        if(orderHistoriesGroupByCompOrderDate != null){
            return result.Success(orderHistoriesGroupByCompOrderDate);
        }
        return result.Fail("数据为空");
    }
}
