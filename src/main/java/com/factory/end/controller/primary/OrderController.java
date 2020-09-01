package com.factory.end.controller.primary;

import com.factory.end.model.primary.Order;
import com.factory.end.service.primary.OrderService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 17:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
@Api(description = "订单")
@ApiResponses(value = {@ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 400, message = "失败")})
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    Result result;

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation("保存单条订单数据")
    public Result save( @RequestBody @Validated Order order) {
        System.out.println("参数:"+order.toString());
        orderService.save(order);
        return result.Success();
    }

    @PostMapping("/saveAll")
    @ApiOperation("批量保存订单")
    public Result saveAll(@RequestBody @Validated List<Order> orderList) {
        System.out.println("参数:"+orderList);
        orderService.saveAll(orderList);
        return result.Success();
    }

    @GetMapping("/findOrderByOrderNo/{orderNo}")
    @ApiOperation("根据订单号查询")
    public Result findOrderByOrderNo(@PathVariable("orderNo") Integer orderNo) {
        Order orderByOrderNo = orderService.findOrderByOrderNo(orderNo);
        return result.Success(orderByOrderNo);
    }

    @GetMapping("/findOrderByLotName/{lotName}")
    @ApiOperation("根据产品名查询")
    public Result findOrderByLotName(@PathVariable("lotName") String lotName) {
        List<Order> orderByLotName = orderService.findOrderByLotName(lotName);
        return result.Success(orderByLotName);
    }

    @GetMapping("findOrderByKinndName/{kindName}")
    @ApiOperation("根据产品型号查询")
    public Result findOrderByKinndName(@PathVariable("kindName") String kindName) {
        List<Order> orderByKindName = orderService.findOrderByKindName(kindName);
        return result.Success(orderByKindName);
    }

    @GetMapping("/findOrderByKindClass/{kindClass}")
    @ApiOperation("根据产品类型查询")
    public Result findOrderByKindClass(@PathVariable("kindClass") String kindClass) {
        List<Order> orderByKindClass = orderService.findOrderByKindClass(kindClass);
        return result.Success(orderByKindClass);
    }

    @GetMapping("/findOrdersByUserName/{username}")
    @ApiOperation("根据下单人员名查询")
    public Result findOrdersByUserName(@PathVariable("username") String username) {
        List<Order> orderByUserName = orderService.findOrdersByUserName(username);
        return result.Success(orderByUserName);
    }

    @GetMapping("/findOrdersByOrderPlaceDate/{startTime}/{endTime}")
    @ApiOperation("根据下单时间段查询")
    public Result findOrdersByOrderPlaceDate(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime) {
        List<Order> ordersByOrderPlaceDateBetween = orderService.findOrdersByOrderPlaceDate(startTime, endTime);
        return result.Success(ordersByOrderPlaceDateBetween);
    }

    @GetMapping("/findOrdersByConfirmOrderFlg/{orderStatus}")
    @ApiOperation("根据订单状态查询 1，未确认" + "2，确认" + "3，排单中" + "4，回退" + "5，待生产" + "6，生产中" + "7，生产完成")
    public Result findOrdersByConfirmOrderFlg(@PathVariable("orderStatus") Integer orderStatus) {
        List<Order> ordersByConfirmOrderFlg = orderService.findOrdersByOrderStatus(orderStatus);
        return result.Success(ordersByConfirmOrderFlg);
    }

    @GetMapping("/findAll")
    @ApiOperation("查询所有订单信息")
    public Result findAll() {
        Iterable<Order> iOrderMapperAll = orderService.findAll();
        return result.Success(iOrderMapperAll);
    }

    @DeleteMapping("/deleteOrderByOrderNo/{orderNo}")
    @ApiOperation(("根据订单号删除订单"))
    public Result deleteOrderByOrderNo(@PathVariable("orderNo") Integer orderNo){
        boolean flag = orderService.deleteOrderByOrderNo(orderNo);
        if(flag){
            return result.Success();
        }else {
            return result.Fail("订单不存在");
        }
    }

    @DeleteMapping("/deleteOrdersByUserName/{username}")
    @ApiOperation("根据下单人员删除订单")
    public Result deleteOrdersByUserName(@PathVariable("username") String username){
        boolean flag = orderService.deleteOrdersByUserName(username);
        if(flag){
            return result.Success();
        }else {
            return result.Fail("订单不存在!");
        }
    }

    @GetMapping("/existsOrderByOrderNo/{orderNo}")
    @ApiOperation("根据订单号判断订单是否存在")
    public Result existsOrderByOrderNo(@PathVariable("orderNo") Integer orderNo){
        boolean flag = orderService.existsOrderByOrderNo(orderNo);
        if(flag){
            return result.Success("此订单存在!");
        }else {
            return result.Fail("此订单不存在!");
        }
    }

    @PutMapping("/updateOrder/{order}")
    @ApiOperation("修改单条订单")
    public Result updateOrder(@Validated @RequestBody Order order){
        Order updateOrder = orderService.updateOrder(order);
        if(updateOrder != null){
            return result.Success(updateOrder);
        }else {
            return result.Fail();
        }
    }

    @PutMapping("/updateOrder/{orders}")
    @ApiOperation("批量修改订单")
    public Result updateOrders(@Validated @RequestBody List<Order> orderList){
        List<Order> updateOrders = orderService.updateOrders(orderList);
        if(updateOrders != null){
            return result.Success(updateOrders);
        }else {
            return result.Fail();
        }
    }

}
