package com.factory.end.controller.second;

import com.factory.end.dto.second.DyInternalHistoryDto;
import com.factory.end.service.second.DyInternalHistoryService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.*;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/13 10:43
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/in")
@Api(description = "内标历史数据")

public class DyInternalHistoryController {

    @Autowired
    DyInternalHistoryService dyInternalHistoryService;

    @Autowired
    Result result;

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有内标历史数据")
    public Result getAllDYInternalHistory(){
        List<DyInternalHistoryDto> dyInternalHistoryDtos = dyInternalHistoryService.findAll();
        System.out.println("查询有值:");
        for (DyInternalHistoryDto dyInternalHistory:dyInternalHistoryDtos) {
            System.out.println(dyInternalHistory.toString());
        }
        return result.Success(dyInternalHistoryDtos);
    }

    @GetMapping(value = "/findById")
    @ApiOperation(value = "根据id查询内标历史数据")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "成功"),
                @ApiResponse(code = 200,message = "失败"),
                @ApiResponse(code = 1002,message = "缺少参数")})
    public Result getDYInternalHistoryById(@ApiParam("id值") @RequestParam Integer id){
        System.out.println("接收到的参数id:"+id);
        DyInternalHistoryDto dyInternalHistoryById = dyInternalHistoryService.findDYInternalHistoryById(id);

        Cache cache = CacheManager.getInstance().getCache("fpcache");
        Element element = cache.get(1);
        Object objectValue = element.getObjectValue();
        if(objectValue != null){
            DyInternalHistoryDto dyInternalHistoryDto = (DyInternalHistoryDto) objectValue;
            System.out.println("取得的缓存 key:1 value:" + dyInternalHistoryDto);
        }
        else {
            System.out.println("缓存1为空!");
        }

        if(dyInternalHistoryById != null){
            System.out.println("查询到的数据"+dyInternalHistoryById);
        }
        return result.Success(dyInternalHistoryById);
    }
}
