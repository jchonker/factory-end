package com.factory.end.controller.primary;

import com.factory.end.model.primary.Order;
import com.factory.end.service.primary.OrderService;
import com.factory.end.util.http.Result;
import com.factory.end.util.validate.OrderValidationGroups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    Result result;

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation("保存单条订单数据")
    public Result save( @RequestBody @Validated(OrderValidationGroups.Insert.class) Order order) {
        logger.info("order/save");
        logger.info("参数:"+order.toString());
        orderService.save(order);
        return result.Success();
    }

    @PostMapping("/saveAll")
    @ApiOperation("批量保存订单")
    public Result saveAll(@RequestBody @Validated(OrderValidationGroups.Insert.class) List<Order> orderList) {
        System.out.println("/saveAll");
        System.out.println("参数:"+orderList);
        logger.info("order/saveAll");
        logger.info("参数:"+orderList);
        orderService.saveAll(orderList);
        return result.Success();
    }

    @GetMapping("/findOrderByOrderNo/{orderNo}")
    @ApiOperation("根据订单号查询")
    public Result findOrderByOrderNo(@PathVariable("orderNo") String orderNo) {
        logger.info("order/findOrderByOrderNo");
        logger.info("参数:"+orderNo);
        Order orderByOrderNo = orderService.findOrderByOrderNo(orderNo);
        return result.Success(orderByOrderNo);
    }

    @GetMapping("/findOrderByLotName/{lotName}")
    @ApiOperation("根据产品名查询")
    public Result findOrderByLotName(@PathVariable("lotName") String lotName) {
        logger.info("order/findOrderByLotName");
        logger.info("参数：lotName:"+lotName);
        List<Order> orderByLotName = orderService.findOrderByLotName(lotName);
        return result.Success(orderByLotName);
    }

    @GetMapping("findOrderByKindName/{kindName}")
    @ApiOperation("根据产品型号查询")
    public Result findOrderByKindName(@PathVariable("kindName") String kindName) {
        logger.info("order/findOrderByKinndName");
        logger.info("参数: kindName:"+kindName);
        List<Order> orderByKindName = orderService.findOrderByKindName(kindName);
        return result.Success(orderByKindName);
    }

    @GetMapping("/findOrderByKindClass/{kindClass}")
    @ApiOperation("根据产品类型查询")
    public Result findOrderByKindClass(@PathVariable("kindClass") String kindClass) {
        logger.info("order/findOrderByKindClass");
        logger.info("参数 kindClass:"+kindClass);
        List<Order> orderByKindClass = orderService.findOrderByKindClass(kindClass);
        return result.Success(orderByKindClass);
    }

    @GetMapping("/findOrdersByUserName/{username}")
    @ApiOperation("根据下单人员名查询")
    public Result findOrdersByUserName(@PathVariable("username") String username) {
        logger.info("order/findOrdersByUserName");
        logger.info("参数: username:" + username);
        List<Order> orderByUserName = orderService.findOrdersByUserName(username);
        return result.Success(orderByUserName);
    }

    @GetMapping("/findOrdersByOrderPlaceDate/{startTime}/{endTime}")
    @ApiOperation("根据下单时间段查询")
    public Result findOrdersByOrderPlaceDate(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime) {
        logger.info("order/findOrdersByOrderPlaceDate");
        logger.info("参数 startTime:"+startTime+" endTime:"+endTime);
        List<Order> ordersByOrderPlaceDateBetween = orderService.findOrdersByOrderPlaceDate(startTime, endTime);
        return result.Success(ordersByOrderPlaceDateBetween);
    }

    @GetMapping("/findOrdersByConfirmOrderFlg/{orderStatus}")
    @ApiOperation("根据订单状态查询 1，未确认" + "2，确认" + "3，排单中" + "4，回退" + "5，待生产" + "6，生产中" + "7，生产完成")
    public Result findOrdersByConfirmOrderFlg(@PathVariable("orderStatus") Integer orderStatus) {
        logger.info("order/findOrdersByConfirmOrderFlg");
        logger.info("参数 orderStatus:"+orderStatus);
        List<Order> ordersByConfirmOrderFlg = orderService.findOrdersByOrderStatus(orderStatus);
        return result.Success(ordersByConfirmOrderFlg);
    }

    @GetMapping("/findAll")
    @ApiOperation("查询所有订单信息")
    public Result findAll() {
        logger.info("/order/findAll");
        Iterable<Order> iOrderMapperAll = orderService.findAll();
        return result.Success(iOrderMapperAll);
    }

    @DeleteMapping("/deleteOrderByOrderNo/{orderNo}")
    @ApiOperation("根据订单号删除订单")
    public Result deleteOrderByOrderNo(@PathVariable("orderNo") String orderNo){
        logger.info("order/deleteOrderByOrderNo");
        logger.info("参数 orderNo:"+orderNo);
        //先判断订单是否存在
        boolean flag = orderService.existsOrderByOrderNo(orderNo);
        if(flag){
            Integer i = orderService.deleteOrderByOrderNo(orderNo);
            if(i != 0){
                return result.Success("删除成功");
            }
            else {
                return result.Fail("订单删除失败");
            }
        }else {
            return result.Fail("此订单不存在");
        }
    }

    @DeleteMapping("/deleteOrdersByUserName/{username}")
    @ApiOperation("根据下单人员删除订单")
    public Result deleteOrdersByUserName(@PathVariable("username") String username){
        logger.info("order/deleteOrdersByUserName");
        logger.info("参数 username"+username);
        boolean flag = orderService.existsOrdersByUserName(username);
        if(flag){
            Integer i = orderService.deleteOrdersByUserName(username);
            if(i != 0){
                return result.Success("删除成功");
            }
            else {
                return result.Fail("订单删除失败");
            }
        }else {
            return result.Fail("此人员名下没有订单");
        }
    }

    @GetMapping("/existsOrderByOrderNo/{orderNo}")
    @ApiOperation("根据订单号判断订单是否存在")
    public Result existsOrderByOrderNo(@PathVariable("orderNo") String orderNo){
        logger.info("order/existsOrderByOrderNo");
        logger.info("参数 orderNo:"+orderNo);
        boolean flag = orderService.existsOrderByOrderNo(orderNo);
        if(flag){
            return result.Success("订单存在");
        }
        else {
            return result.Fail("此订单不存在");
        }
    }

    @GetMapping("/existsOrdersByUserName/{username}")
    @ApiOperation("根据下单人员名判断订单是否存在")
    public Result existsOrdersByUserName(@PathVariable("username") String username){
        logger.info("order/existsOrdersByUserName");
        logger.info("参数 username:"+username);
        boolean flag = orderService.existsOrdersByUserName(username);
        if(flag){
            return result.Success("订单存在");
        }else {
            return result.Fail("此人员名下没有订单");
        }
    }

    @PutMapping("/updateOrder")
    @ApiOperation("修改单条订单")
    public Result updateOrder(@Validated(OrderValidationGroups.Update.class) @RequestBody Order order){
        logger.info("order/updateOrder");
        logger.info("参数 order:"+order);
        Order updateOrder = orderService.updateOrder(order);
        if(updateOrder != null){
            return result.Success(updateOrder);
        }else {
            return result.Fail();
        }
    }

    @PutMapping("/updateOrders")
    @ApiOperation("批量修改订单")
    public Result updateOrders(@Validated(OrderValidationGroups.Update.class) @RequestBody List<Order> orderList){
        logger.info("order/updateOrders");
        logger.info("参数 orderList:"+orderList);
        List<Order> updateOrders = orderService.updateOrders(orderList);
        if(updateOrders != null){
            return result.Success(updateOrders);
        }else {
            return result.Fail();
        }
    }

    @GetMapping("/findAllOrdersByPage/{currentPage}/{pageSize}")
    @ApiOperation("分页查询所有数据")
    public Result findAllOrdersPage(@PathVariable("currentPage") Integer currentPage,@PathVariable("pageSize") Integer pageSize){
        logger.info("order/findAllOrdersByPage");
        logger.info("分页查询 currentPage:"+currentPage+" pageSize:"+pageSize);
        //同步前端传回的当前页参数
        Integer currentPageReal = currentPage - 1;
        Page<Order> allByPage = orderService.findAllByPage(currentPageReal, pageSize);
        return result.Success(allByPage);
    }

    @GetMapping("/findByPage/{currentPage}/{pageSize}")
    @ApiOperation("多条件分页查询")
    public Result findByPage(@ModelAttribute Order order,@PathVariable("currentPage") Integer currentPage,@PathVariable("pageSize") Integer pageSize){
        logger.info("order/findByPage");
        logger.info("多条件分页查询:");
        logger.info("order:"+order);
        logger.info("currentPage:"+currentPage+" pageSize:"+pageSize);
        //同步前端传回的当前页参数
        Integer currentPageReal = currentPage - 1;
        Page<Order> byPage = orderService.findByPage(order, currentPageReal, pageSize);
        return result.Success(byPage);
    }

}
