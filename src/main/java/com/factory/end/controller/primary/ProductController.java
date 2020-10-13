package com.factory.end.controller.primary;

import com.factory.end.model.primary.Product;
import com.factory.end.service.primary.ProductService;
import com.factory.end.util.http.Result;
import com.factory.end.util.validate.ProductValidationGroups;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private Result result;

    @GetMapping("/existsByProductNo/{productNo}")
    @ApiOperation("根据产品号查询订单是否存在")
    public Result existsByProductNo(@PathVariable String productNo){
        boolean exists = productService.existsByProductNo(productNo);
        return result.Success("订单存在");
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/findAll")
    @ApiOperation("查询所有数据")
    public Result findAll(){
        logger.info("product/findAll");
        List<Product> products = productService.findAll();
        return result.Success(products);
    }

    @GetMapping("/findAllByOrderNo/{orderNo}")
    @ApiOperation("根据订单号查询")
    public Result findAllByOrderNo(@PathVariable("orderNo") String orderNo){
        logger.info("product/findAllByOrderNo");
        logger.info("参数 orderNo:"+orderNo);
        List<Product> allByOrderNo = productService.findAllByOrderNo(orderNo);
        return result.Success(allByOrderNo);
    }

    @GetMapping("/findAllByProductNo/{productNo}")
    @ApiOperation("根据生产号查询")
    public Result findAllByProductNo(@PathVariable("productNo") String productNo){
        logger.info("product/findAllByProductNo");
        logger.info("参数 productNo:"+productNo);
        List<Product> allByProductNo = productService.findAllByProductNo(productNo);
        return result.Success(allByProductNo);
    }

    @GetMapping("/findAllByEquipmentNo/{equNo}")
    @ApiOperation("根据设备号查询")
    public Result findAllByEquipmentNo(@PathVariable("equNo") String equNo){
        logger.info("product/findAllByEquipmentNo");
        logger.info("参数 equNo:"+equNo);
        List<Product> allByEquipmentNo = productService.findAllByEquipmentNo(equNo);
        return result.Success(allByEquipmentNo);
    }

    @GetMapping("/findAllByStartDate/{startDate}/{endDate}")
    @ApiOperation("根据生产开始时间段查询")
    public Result findAllByStartDate(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endState){
        logger.info("product/findAllByStartDate");
        logger.info("参数 startDate:"+startDate+" endState:"+endState);
        List<Product> allByStartDateBetween = productService.findAllByStartDateBetween(startDate, endState);
        return result.Success(allByStartDateBetween);
    }

    @GetMapping("/findAllByCoAndCompProductFlg/{orderStatus}")
    @ApiOperation("根据生产完成标志查询")
    public Result findAllByCoAndCompProductFlg(@PathVariable("orderStatus") Integer orderStatus){
        logger.info("product/findAllByCoAndCompProductFlg");
        logger.info("参数 compProductFlg:"+orderStatus);
        List<Product> allByCoAndCompProductFlg = productService.findAllByOrderStatus(orderStatus);
        return result.Success(allByCoAndCompProductFlg);
    }

    @PostMapping("/saveProduct")
    @ApiOperation("新增生产")
    public Result saveProduct(@Validated(ProductValidationGroups.Insert.class) @RequestBody Product product){
        logger.info("product/saveProduct");
        logger.info("参数 product:"+product);
        Product saveProduct = productService.saveProduct(product);
        return result.Success(saveProduct);
    }

    @PostMapping("/saveProducts")
    @ApiOperation("批量新增生产")
    public Result saveProducts(@Validated(ProductValidationGroups.Insert.class) @RequestBody List<Product> productList){
        logger.info("product/saveProducts");
        logger.info("参数 productList:"+productList);
        List<Product> products = productService.saveProducts(productList);
        return result.Success(products);
    }

    @Transactional
    @PutMapping("/updateProduct")
    @ApiOperation("修改生产")
    public Result updateProduct(@Validated(ProductValidationGroups.Update.class) @RequestBody Product product){
        logger.info("product/updateProduct");
        logger.info("参数 product:"+product);
        //先根据订单id查询订单是否存在
        boolean exists = productService.existsByOrderNo(product.getOrderNo());
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
    public Result updateProducts(@Validated(ProductValidationGroups.Update.class) @RequestBody List<Product> productList){
        logger.info("product/updateProducts");
        logger.info("参数 productList:"+productList);
        //先根据订单id查询订单是否存在
        for(Product product:productList){
            boolean exists = productService.existsByOrderNo(product.getOrderNo());
            if(!exists){
                return result.Fail("订单"+product.getId()+"不存在!");
            }
        }
        List<Product> products = productService.saveProducts(productList);
        return result.Success(products);
    }

    @GetMapping("/findByPage/{currentPage}/{pageSize}")
    @ApiOperation("多条件分页查询")
    public Result findByPage(@ModelAttribute Product product,@PathVariable("currentPage") Integer currentPage,@PathVariable("pageSize") Integer pageSize){
        logger.info("product/findByPage");
        logger.info("多条件分页查询:");
        logger.info("product:"+product);
        logger.info("currentPage:"+currentPage+" pageSize:"+pageSize);
        //同步前端传回的当前页参数
        Integer currentPageReal = currentPage - 1;
        Page<Product> byPage = productService.findByPage(product, currentPageReal, pageSize);
        return result.Success(byPage);
    }

    @PutMapping("/updateCollectValueByProductNo/{productNo}/{collectValue}")
    @ApiOperation("根据生产号修改生产值")
    public Result updateCollectValueByProductNo( @PathVariable String productNo,@PathVariable Integer collectValue){
        boolean exists = productService.existsByProductNo(productNo);
        if(exists){
            productService.updateCollectValueByProductNo(productNo,collectValue);
            return result.Success();
        }
        else {
            return result.Fail("此订单不存在!");
        }
    }

    @PutMapping("/updateCompProductDateByProductNo/{productNo}/{compProductDate}")
    @ApiOperation("根据产品号修改订单完成时间")
    public Result updateCompProductDateByProductNo(@PathVariable String productNo,@PathVariable String compProductDate){
        boolean exists = productService.existsByProductNo(productNo);
        if(exists){
            productService.updateCompProductDateByProductNo(productNo,compProductDate);
            return result.Success();
        }
        else {
            return result.Fail("此订单不存在");
        }
    }

    @GetMapping("/findCountByOrderNo/{orderNo}")
    @ApiOperation(("根据订单号查询记录数量"))
    public Result findCountByOrderNo(@PathVariable String orderNo){
        Integer countByOrderNo = productService.findCountByOrderNo(orderNo);
        return result.Success(countByOrderNo);
    }
}
