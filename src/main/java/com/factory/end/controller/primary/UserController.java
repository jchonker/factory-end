package com.factory.end.controller.primary;

import com.factory.end.dto.second.UserDto;
import com.factory.end.model.primary.User;
import com.factory.end.service.primary.UserService;
import com.factory.end.util.JwtUtils;
import com.factory.end.util.http.JsonResult;
import com.factory.end.util.http.Result;
import com.factory.end.util.http.ResultCode;
import com.factory.end.util.http.ResultTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/10/22 11:12
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
@Api(description = "用户")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Result result;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * 注册
     * @param username
     * @param password
     * @param roles
     * @return
     */
    @PostMapping("/registy/{username}/{password}/{roles}")
    @ApiOperation("注册")
    @ResponseBody
    public Result registy(@PathVariable String username,@PathVariable String password,@PathVariable String roles){
        userService.registry(username,password,roles);
        return result.Success();
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login/{username}/{password}")
    @ApiOperation("登录")
    @ResponseBody
    public JsonResult login(@PathVariable String username, @PathVariable String password,HttpServletRequest request){
        logger.info("username:"+username);
        logger.info("password:"+password);
        JsonResult jsonResult = null;
        UserDto userDto = userService.findByUserName(username);
        //查询用户名是否存在
        if(userDto != null){
            //用户是否可用
            if(userDto.isEnable()){
                //对比密码是否正确
                if(encoder.matches(password,userDto.getPassword())){
                    //生成token
                    String token = JwtUtils.createToken(username, userDto.getRoles());
                    String tokenStr = JwtUtils.TOKEN_PREFIX + token;
                    logger.info("tokenStr:"+tokenStr);
                    jsonResult = ResultTool.success(tokenStr);
                }
                else {
                    jsonResult = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
                    logger.error("用户密码不正确");
                }
            }
            else {
                logger.error("用户不可用");
                jsonResult = ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
            }
        }
        else {
            logger.error("用户不存在");
            jsonResult = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        return jsonResult;
    }


    /**
     * 设置用户是否可用
     * @return
     */
    @PutMapping("/enable/{username}/{enable}")
    @ApiOperation("设置用户是否可用")
    @ResponseBody
    public JsonResult enableUser(@ApiParam(value = "用户名",required = true) @PathVariable String username,@ApiParam(name = "enable",value = "用户是否可用",required = true,allowableValues = "0,1") @PathVariable Integer enable){
        //先判断用户是否存在
        User user = userService.findUserByUserName(username);
        if(user != null){
            userService.updateEnableByUserName(username, enable);
            return ResultTool.success();
        }
        else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/listUser")
    @ApiOperation("查询所有用户")
    @ResponseBody
    public JsonResult listUser(){
        List<User> users = userService.listUser();
        //去除密码项
        for(User user:users){
            user.setPassword("");
        }
        return ResultTool.success(users);
    }

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    @GetMapping("/exist/{username}")
    @ApiOperation("判断用户是否存在")
    @ResponseBody
    public JsonResult existUser(@PathVariable String username){
        User user = userService.findUserByUserName(username);
        if(user != null){
            return ResultTool.success(true);
        }
        else {
            return ResultTool.success(false);
        }
    }

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    @PutMapping("/updatePassword/{username}/{password}")
    @ApiOperation("修改密码")
    @ResponseBody
    public JsonResult updatePasswordByUserName(@PathVariable String username,@PathVariable String password){
        User user = userService.findUserByUserName(username);
        if(user != null){
            //判断密码是否相同
            UserDto userDto = userService.findByUserName(username);
            //密码不相同
            if(!new BCryptPasswordEncoder().matches(password,userDto.getPassword())){
                //密码加密
                userService.updatePasswordByUserName(username,new BCryptPasswordEncoder().encode(password));
                return ResultTool.success("修改密码成功");
            }
            else {
                return ResultTool.fail(ResultCode.USER_ACCOUNT_PASSWORD_EQULS);
            }
        }
        else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    /**
     * 删除用户
     * @param username
     * @return
     */
    @DeleteMapping("deleteUser/{username}")
    @ApiOperation("删除用户")
    @ResponseBody
    public JsonResult deleteUserByUsername(@PathVariable String username){
        User user = userService.findUserByUserName(username);
        if(user != null){
            userService.deleteUserByUsername(username);
            return ResultTool.success("删除用户成功");
        }else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @GetMapping("findUser/{username}")
    @ApiOperation("根据用户名查询用户")
    @ResponseBody
    public JsonResult findUserByUserName(@PathVariable String username){
        User user = userService.findUserByUserName(username);
        //去除密码项
        user.setPassword("");
        return ResultTool.success(user);
    }

    @GetMapping("/findAllByEnable/{enable}")
    @ApiOperation("根据账号是否可用进行查询")
    @ResponseBody
    public JsonResult findAllByEnable(@ApiParam(name = "enable",value = "用户是否可用",required = true,allowableValues = "0,1") @PathVariable Integer enable){
        List<User> userList = userService.findUserByEnable(enable);
        if(userList != null){
            return ResultTool.success(userList);
        }
        return ResultTool.success("数据为空");
    }

    @GetMapping("/findAllByRole/{roles}")
    @ApiOperation("根据角色查询")
    @ResponseBody
    public JsonResult findAllByRoles(@PathVariable String roles){
        List<User> userList = userService.findUserByRoles(roles);
        if(userList != null){
            return ResultTool.success(userList);
        }
        return ResultTool.success("数据为空");
    }
}
