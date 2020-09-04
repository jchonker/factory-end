package com.factory.end.controller.primary;

import com.factory.end.dto.primary.StatusDto;
import com.factory.end.model.primary.Status;
import com.factory.end.service.primary.StatusService;
import com.factory.end.util.http.HttpStatusEnum;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:58
 * @Version 1.0
 */
@Api(description = "状态")
@Controller
@RequestMapping("/status")
public class StatusController {
    private Logger logger = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private StatusService statusService;

    @Autowired
    Result result;

    @GetMapping("/findAll")
    @ApiOperation("查询所有设备的状态信息")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "失败")})
    @ResponseBody
    public Result findAllStatus() {
        logger.info("status/findAll");
        List<Status> statusList = statusService.findAll();
        if(statusService != null){
            return result.Success(statusList);
        }
        return result.Fail();
    }
}
