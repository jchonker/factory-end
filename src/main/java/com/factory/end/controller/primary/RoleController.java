package com.factory.end.controller.primary;

import com.factory.end.model.primary.Role;
import com.factory.end.service.primary.RoleService;
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

/**
 * @Author jchonker
 * @Date 2020/11/5 13:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/role")
@Api(description = "角色")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @Autowired
    Result result;

    @GetMapping("/findAll")
    @ApiOperation("查询全部")
    public Result findAll(){
        List<Role> roleList = roleService.findAll();
        if(roleList == null){
            return result.Success();
        }
        return result.Success(roleList);
    }
}
