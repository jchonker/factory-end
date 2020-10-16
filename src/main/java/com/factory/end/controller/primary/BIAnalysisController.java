package com.factory.end.controller.primary;

import com.factory.end.service.primary.OrderService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
 * @Date 2020/9/22 11:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/BI")
@Api(description = "BI分析")
@ApiResponses(value = {@ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 400, message = "失败")})
public class BIAnalysisController {
    private Logger logger = LoggerFactory.getLogger(BIAnalysisController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    Result result;

    @GetMapping("/order/findOrderStatusAndCountByBI")
    @ApiOperation("order表BI分析查询订单状态和其数量")
    public Result findOrderStatusAndCountByBI(){
        List<Map<String,Integer>> orderStatusAndCountByBI = orderService.findOrderStatusAndCountByBI();
        return result.Success(orderStatusAndCountByBI);
    }

    @GetMapping("/order/findLotNameAndCountByBI")
    @ApiOperation("order表BI分析查询产品和其数量")
    public Result findLotNameAndCountByBI(){
        List<Map<String, Integer>> lotNameAndCountByBI = orderService.findLotNameAndCountByBI();
        return result.Success(lotNameAndCountByBI);
    }

    @GetMapping("/order/findKindClassAndCountByBI")
    @ApiOperation("order表BI分析查询产品类型和其数量")
    public Result findKindClassAndCountByBI(){
        List<Map<String, Integer>> kindClassAndCountByBI = orderService.findKindClassAndCountByBI();
        return result.Success(kindClassAndCountByBI);
    }

    @GetMapping("/order/findUserNameAndCountByBI")
    @ApiOperation("order表BI分析查询下单人员名和其数量")
    public Result findUserNameAndCountByBI(){
        List<Map<String, Integer>> userNameAndCountByBI = orderService.findUserNameAndCountByBI();
        return result.Success(userNameAndCountByBI);
    }

    @GetMapping("/order/findOrdersGroupByBrand")
    @ApiOperation("品牌分组查询")
    public Result findOrdersGroupByBrand(){
        List<Map<String, Map<String, Integer>>> ordersGroupByBrand = orderService.findOrdersGroupByBrand();
        return result.Success(ordersGroupByBrand);
    }
}
