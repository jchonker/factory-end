package com.factory.end.controller.second;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author jchonker
 * @Date 2020/8/21 14:35
 * @Version 1.0
 */
@Controller
public class LoginController {
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login_page";
    }

    /**
     * 登录成功跳转页面
     * @return
     */
    @RequestMapping("/login_success")
    public String login_success(){
        return "login_success";
    }

    /**
     * 跳转失败页面
     * @return
     */
    @RequestMapping("login_fail")
    public String login_fail(){
        return "login_fail";
    }
}
