package com.factory.end.controller.primary;

import com.factory.end.dto.primary.UserDto;
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
     * @param username 用户名
     * @param password 密码
     * @param roles 角色
     * @return
     */
    @PostMapping("/registry/{username}/{password}/{roles}")
    @ApiOperation("注册")
    @ResponseBody
    public JsonResult registy(@PathVariable String username,@PathVariable String password,@PathVariable Integer roles){
        UserDto userDto = userService.findByUserName(username);
        if(userDto != null){
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        userService.registry(username,password,roles);
        return ResultTool.success();
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
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
        if(userDto == null){
            logger.error("用户不存在");
            jsonResult = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
            return jsonResult;
        }
        //用户是否可用
        if(!userDto.isEnable()){
            logger.error("用户不可用");
            jsonResult = ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
            return jsonResult;
        }
        //用户账户锁定
        if(!userDto.isAccountNonLocked()){
            logger.error("用户可账户锁定");
            jsonResult = ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
            return jsonResult;
        }
        //用户账号过期
        if(!userDto.isAccountNonExpired()){
            logger.error("用户账户过期");
            jsonResult = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
            return jsonResult;
        }
        //用户密码过期
        if(!userDto.isCredentialsNonExpired()){
            logger.error("密码过期");
            jsonResult = ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
            return jsonResult;
        }
        //对比密码是否正确
        if(!encoder.matches(password,userDto.getPassword())){
            jsonResult = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
            logger.error("用户密码不正确");
            return jsonResult;
        }
        //生成token
        String token = JwtUtils.createToken(username, userDto.getRoles().toString());
        String tokenStr = JwtUtils.TOKEN_PREFIX + token;
        logger.info("tokenStr:"+tokenStr);
        jsonResult = ResultTool.success(tokenStr);

        return jsonResult;
    }


    /**
     * 设置用户是否可用
     * @param username 用户名
     * @param enable 是否可用
     * @return
     */
    @PutMapping("/enable/{username}/{enable}")
    @ApiOperation("设置用户是否可用")
    @ResponseBody
    public JsonResult enableUser(@ApiParam(value = "用户名",required = true) @PathVariable String username,@ApiParam(name = "enable",value = "用户是否可用",required = true,allowableValues = "0,1") @PathVariable Integer enable){
        //先判断用户是否存在
        UserDto userDto = userService.findByUserName(username);
        if(userDto != null){
            userService.updateEnableByUserName(username, enable);
            return ResultTool.success();
        }
        else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    /**
     * 设置用户是否未锁定
     * @param username
     * @param accountNonLocked
     * @return
     */
    @PutMapping("/accountNonLocked/{username}/{accountNonLocked}")
    @ApiOperation("设置用户是否未锁定")
    @ResponseBody
    public JsonResult updateAccountNonLockedByUserName(@ApiParam(value = "用户名",required = true) @PathVariable String username,@ApiParam(name = "accountNonLocked",value = "用户是否未锁定",required = true,allowableValues = "0,1") @PathVariable Integer accountNonLocked){
        //先判断用户是否存在
        UserDto userDto = userService.findByUserName(username);
        if(userDto != null){
            userService.updateAccountNonLockedByUserName(username, accountNonLocked);
            return ResultTool.success();
        }
        else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }


    /**
     * 设置用户是否未过期
     * @param username
     * @param accountNonExpired
     * @return
     */
    @PutMapping("/accountNonExpired/{username}/{accountNonExpired}")
    @ApiOperation("设置用户是否未过期")
    @ResponseBody
    public JsonResult updateAccountNonExpiredByUserName(@ApiParam(value = "用户名",required = true) @PathVariable String username,@ApiParam(name = "accountNonExpired",value = "设置用户是否未过期",required = true,allowableValues = "0,1") @PathVariable Integer accountNonExpired){
        String string = accountNonExpired.getClass().toString();
        logger.info("accountNonExpired数据类型:"+string);
        //先判断用户是否存在
        UserDto userDto = userService.findByUserName(username);
        if(userDto != null){
            userService.updateAccountNonExpiredByUserName(username, accountNonExpired);
            return ResultTool.success();
        }
        else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    /**
     * 设置密码是否未过期
     * @param username
     * @param credentialsNonExpired
     * @return
     */
    @PutMapping("/credentialsNonExpired/{username}/{credentialsNonExpired}")
    @ApiOperation("设置密码是否未过期")
    @ResponseBody
    public JsonResult updateCredentialsNonExpiredByUserName(@ApiParam(value = "用户名",required = true) @PathVariable String username,@ApiParam(name = "credentialsNonExpired",value = "设置密码是否未过期",required = true,allowableValues = "0,1") @PathVariable Integer credentialsNonExpired){
        //先判断用户是否存在
        UserDto userDto = userService.findByUserName(username);
        if(userDto != null){
            userService.updateCredentialsNonExpiredByUserName(username, credentialsNonExpired);
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
        List<UserDto> users = userService.listUser();
        if(users != null){
            //去除密码项
            for(UserDto user:users){
                user.setPassword("");
            }
            return ResultTool.success(users);
        }
        else {
            return ResultTool.success();
        }
    }

    /**
     * 判断用户是否存在
     * @param username 用户名
     * @return
     */
    @GetMapping("/exist/{username}")
    @ApiOperation("判断用户是否存在")
    @ResponseBody
    public JsonResult existUser(@PathVariable String username){
        UserDto userDto = userService.findByUserName(username);
        if(userDto != null){
            return ResultTool.success(true);
        }
        else {
            return ResultTool.success(false);
        }
    }

    /**
     * 修改密码
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PutMapping("/updatePassword/{username}/{password}")
    @ApiOperation("修改密码")
    @ResponseBody
    public JsonResult updatePasswordByUserName(@PathVariable String username,@PathVariable String password){
        UserDto userDto = userService.findByUserName(username);
        if(userDto != null){
            //判断密码是否相同
            UserDto compUserDto = userService.findByUserName(username);
            //密码不相同
            if(!new BCryptPasswordEncoder().matches(password,compUserDto.getPassword())){
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
     * @param username 用户名
     * @return
     */
    @DeleteMapping("deleteUser/{username}")
    @ApiOperation("删除用户")
    @ResponseBody
    public JsonResult deleteUserByUsername(@PathVariable String username){
        UserDto userDto = userService.findByUserName(username);
        if(userDto != null){
            userService.deleteUserByUsername(username);
            return ResultTool.success("删除用户成功");
        }else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return
     */
    @GetMapping("findUser/{username}")
    @ApiOperation("根据用户名查询用户")
    @ResponseBody
    public JsonResult findUserByUserName(@PathVariable String username){
        UserDto userDto = userService.findByUserName(username);
        //去除密码项
        userDto.setPassword("");
        return ResultTool.success(userDto);
    }

    /**
     * 根据账号是否可用进行查询
     * @param enable
     * @return
     */
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

    /**
     * 根据角色查询
     * @param roles 角色
     * @return
     */
//    @GetMapping("/findAllByRole/{roles}")
//    @ApiOperation("根据角色查询")
//    @ResponseBody
//    public JsonResult findAllByRoles(@PathVariable String roles){
//        List<User> userList = userService.findUserByRoles(roles);
//        if(userList != null){
//            return ResultTool.success(userList);
//        }
//        return ResultTool.success("数据为空");
//    }
}
