package com.factory.end.controller.primary;

import com.factory.end.model.primary.ProjectDetails;
import com.factory.end.service.primary.ProjectDetailsService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.print.event.PrintJobEvent;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/9/15 13:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/projectDetails")
@Api(description = "项目详情")
public class ProjectDetailsController {
    private Logger logger = LoggerFactory.getLogger(ProjectDetailsController.class);

    @Autowired
    private ProjectDetailsService projectDetailsService;

    @Autowired
    private Result result;

    @PostMapping("/save")
    @ApiOperation("新增")
    public Result save(@RequestBody ProjectDetails projectDetails){
        projectDetailsService.save(projectDetails);
        return result.Success();
    }

    @PutMapping("/update")
    @ApiOperation("修改")
    public Result update(@RequestBody ProjectDetails projectDetails){
        projectDetailsService.update(projectDetails);
        return result.Success();
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody ProjectDetails projectDetails){
        projectDetailsService.delete(projectDetails);
        return result.Success();
    }

    @DeleteMapping("/delete/{projectNo}")
    @ApiOperation("根据项目编号删除")
    public Result deleteByProjectNo(@PathVariable String projectNo){
        projectDetailsService.deleteByProjectNo(projectNo);
        return result.Success();
    }

    @DeleteMapping("/deleteById/{id}")
    @ApiOperation("根据id删除")
    public Result deleteById(@PathVariable Integer id){
        projectDetailsService.deleteById(id);
        return result.Success();
    }

    @GetMapping("/findAll")
    @ApiOperation("查询所有")
    public Result findAll(){
        List<ProjectDetails> detailsList = projectDetailsService.findAll();
        return result.Success(detailsList);
    }

    @GetMapping("/findByPage/{currentPage}/{pageSize}")
    @ApiOperation("复杂分页查询")
    public Result findByPage(@ModelAttribute ProjectDetails projectDetails,@PathVariable("currentPage") Integer currentPage,@PathVariable("pageSize") Integer pageSize){
        logger.info("projectDetails/findByPage");
        logger.info("复杂分页查询");
        logger.info("参数:");
        logger.info(projectDetailsService.toString());
        logger.info("currentPage:"+currentPage);
        logger.info("pageSize:"+pageSize);
        //同步前端传回的当前页参数
        Integer currentPageReal = currentPage - 1;
        Page<ProjectDetails> byPage = projectDetailsService.findByPage(projectDetails, currentPageReal, pageSize);
        return result.Success(byPage);
    }

}
