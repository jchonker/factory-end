package com.factory.end.controller.primary;

import com.factory.end.model.primary.Product;
import com.factory.end.service.primary.ProductService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 14:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
@Api(description = "生产(分订单表)")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private Result result;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/findAll")
    @ApiOperation("查询所有数据")
    public Result findAll(){
        List<Product> products = productService.findAll();
        return result.Success(products);
    }

    @GetMapping("/findAllByOrderNo/{orderNo}")
    @ApiOperation("根据订单号查询")
    public Result findAllByOrderNo(@PathVariable("orderNo") Integer orderNo){
        List<Product> allByOrderNo = productService.findAllByOrderNo(orderNo);
        return result.Success(allByOrderNo);
    }

    @GetMapping("/findAllByProductNo/{productNo}")
    @ApiOperation("根据生产号查询")
    public Result findAllByProductNo(@PathVariable("productNo") Integer productNo){
        List<Product> allByProductNo = productService.findAllByProductNo(productNo);
        return result.Success(allByProductNo);
    }

    @GetMapping("/findAllByEquipmentNo")
    @ApiOperation("根据设备号查询")
    public Result findAllByEquipmentNo(Integer equNo){
        List<Product> allByEquipmentNo = productService.findAllByEquipmentNo(equNo);
        return result.Success(allByEquipmentNo);
    }

    @GetMapping("/findAllByStartDate/{startDate}/{endDate}")
    @ApiOperation("根据生产开始时间段查询")
    public Result findAllByStartDate(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endState){
        List<Product> allByStartDateBetween = productService.findAllByStartDateBetween(startDate, endState);
        return result.Success(allByStartDateBetween);
    }

    @GetMapping("/findAllByCoAndCompProductFlg/{compProductFlg}")
    @ApiOperation("根据生产完成标志查询")
    public Result findAllByCoAndCompProductFlg(@PathVariable("compProductFlg") Integer compProductFlg){
        List<Product> allByCoAndCompProductFlg = productService.findAllByCompProductFlg(compProductFlg);
        return result.Success(allByCoAndCompProductFlg);
    }

    @PostMapping("/saveProduct")
    @ApiOperation("新增生产")
    public Result saveProduct(@Validated @RequestBody Product product){
        Product saveProduct = productService.saveProduct(product);
        return result.Success(saveProduct);
    }

    @PostMapping("/saveProducts")
    @ApiOperation("批量新增生产")
    public Result saveProducts(@Validated @RequestBody List<Product> productList){
        List<Product> products = productService.saveProducts(productList);
        return result.Success(products);
    }

    @Transactional
    @PutMapping("/updateProduct")
    @ApiOperation("修改生产")
    public Result updateProduct(@RequestBody Product product){
        //先根据订单id查询订单是否存在
        boolean exists = productService.existsByOrderNo(product.getId());
        if(exists){
            Product saveProduct = productService.saveProduct(product);
            return result.Success(saveProduct);
        }
        else {
            return result.Fail("此条订单不存在!");
        }
    }

    @Transactional
    @PutMapping("/updateProducts")
    @ApiOperation("批量修改生产")
    public Result updateProducts(@RequestBody List<Product> productList){
        //先根据订单id查询订单是否存在
        for(Product product:productList){
            boolean exists = productService.existsByOrderNo(product.getId());
            if(!exists){
                return result.Fail("订单"+product.getId()+"不存在!");
            }
        }
        List<Product> products = productService.saveProducts(productList);
        return result.Success(products);
    }

}
