package com.factory.end.controller;

import com.factory.end.service.primary.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jchonker
 * @Date 2020/10/20 17:02
 * @Version 1.0
 * 测试类，模拟用户注册
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @PostMapping("/register")
//    public String registerUser(@RequestBody Map<String,String> registerUser){
//        User user = new User();
//        user.setUsername(registerUser.get("username"));
//        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
//        user.setRoles("ROLE_USER");
//        user.setEnable(true);
//        userService.save(user);
//        return "success";
//    }
}
