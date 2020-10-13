package com.factory.end.controller.primary;

import com.factory.end.model.primary.Workers;
import com.factory.end.service.primary.WorkersService;
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
 * @Date 2020/9/27 14:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/workers")
@Api(description = "员工")
public class WorkersController {
    private Logger logger = LoggerFactory.getLogger(WorkersController.class);

    @Autowired
    private Result result;

    @Autowired
    private WorkersService workersService;

    @PostMapping(value = "/save")
    @ApiOperation("新增")
    public Result save(@RequestBody Workers workers){
        workersService.save(workers);
        return result.Success();
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Workers workers){
        workersService.delete(workers);
        return result.Success();
    }

    @PutMapping(value = "/update")
    @ApiOperation("修改")
    public Result update(@RequestBody Workers workers){
        workersService.update(workers);
        return result.Success();
    }

    @GetMapping("/findByPage/{currentPage}/{pageSize}")
    @ApiOperation("分页查询")
    public Result findByPage(@PathVariable("currentPage") Integer currentPage,@PathVariable("pageSize") Integer pageSize){
        logger.info("workers/findByPage");
        logger.info("分页查询:");
        logger.info("currentPage:"+currentPage+" pageSize:"+pageSize);
        //同步前端传回的当前页参数
        Integer currentPageReal = currentPage - 1;
        Page<Workers> byPage = workersService.findByPage(currentPageReal, pageSize);
        return result.Success(byPage);
    }

    @GetMapping("/findAll")
    @ApiOperation("查询所有")
    public Result findAll(){
        logger.info("workers/findAll");
        List<Workers> workersList = workersService.findAll();
        return result.Success(workersList);
    }
}
