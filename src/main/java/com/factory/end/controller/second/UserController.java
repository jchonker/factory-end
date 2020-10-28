package com.factory.end.controller.second;

import com.alibaba.fastjson.JSON;
import com.factory.end.util.HttpClientPostFs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author jchonker
 * @Date 2020/8/21 16:11
 * @Version 1.0
 * 用户验证
 * 此UserCOntroller和USerService无关
 */
//@Controller
//@RequestMapping(value = "/identity")
public class UserController {
//    @Autowired
//    @Qualifier("consumerTokenServices")
//    private ConsumerTokenServices consumerTokenServices;

//    @Value("${clientSecret}")
//    private String clientSecret;

//    @Autowired
//    HttpClientPostFs httpClientPostFs;

//    /**
//     * 登录获取token
//     * 登录接口不用写,直接使用/oauth/token请求登录
//     * @param request
//     * @param response
//     * @return
//     */
//    @ResponseBody
//    @PostMapping(value = "/login")
//    public void loginMethod(HttpServletRequest request,HttpServletResponse response){
//        System.out.println("client_secrect:"+clientSecret);
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        //http://localhost:8080/oauth/token?username=admin&password=123456&grant_type=password&client_id=password&scope=all&client_secret=123456
//        //获取请求路径(不包含参数的全部路径)
//        //String url = request.getRequestURI();
//        //System.out.println("url:"+url);
//        String new_url = "http://localhost:8080/" + "oauth/token?username="+username+"&password="+password+"&grant_type=password&client_id=password&scope=all&client_secret="+clientSecret;
//        System.out.println("redirect_url:" + new_url);
//
////        System.out.println("重定向获取token:");
////        HttpClientPostFs httpClientPostFs = new HttpClientPostFs(response);
////        try {
////            httpClientPostFs.sendByPost(new_url);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
////        System.out.println("转发获取token:");
////        ModelAndView mv = new ModelAndView("/oauth/token");
//////        mv.setViewName("/oauth/token?username=admin&password=123456&grant_type=password&client_id=password&scope=all&client_secret=123456");
////        //mv.setViewName("oauth/token");
////        mv.addObject("username",username);
////        mv.addObject("password",password);
////        mv.addObject("grant_type","password");
////        mv.addObject("client_id","password");
////        mv.addObject("scope","all");
////        mv.addObject("client_secret","123456");
////        return mv;
//
//        try {
//            request.getRequestDispatcher("/oauth/token?username="+username+"&password="+password+"&grant_type=password&client_id=password&scope=all&client_secret="+clientSecret).forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //return "forword:/"+redirect_url;
//    }
//
//
//    /**
//     * 刷新token
//     * 不用此接口,直接使用/oauth/token接口访问
//     * @param refresh_token
//     * @return
//     */
//    @GetMapping(value = "/refreshToken")
//    public String refreshTokenMethod(@RequestParam("refresh_token") String refresh_token){
//        System.out.println("client_secrect:"+clientSecret);
//        System.out.println("refresh_token:"+refresh_token);
//        //http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=c45a0c03-8cdc-45eb-8700-acb5f2b52e6e&client_id=password&client_secret=123456
//        //获取请求路径(不包含参数的全部路径)
//        //String url = request.getRequestURI();
//        //System.out.println("url:"+url);
//        String redirect_url = "http://localhost:8080/" + "oauth/token?grant_type=refresh_token&refresh_token="+refresh_token+"&client_id=password&client_secret="+clientSecret;
//        System.out.println("redirect_url:"+redirect_url);
//        System.out.println("重定向刷新token");
//        HttpClientPostFs httpClientPostFs = new HttpClientPostFs();
//        try {
//            httpClientPostFs.sendByPost(redirect_url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/"+redirect_url;
//    }
//
//    /**
//     * 带token请求
//     * 退出登录,并清除redis中的token
//     * 奇怪:这里接口名和方法名相同时找不到此接口
//     **/
//    @ResponseBody
//    @GetMapping(value = "/logout")
//    public String clearTokenMethod(@RequestParam("access_token") String access_token){
//        System.out.println("请求退出登录");
//        System.out.println("accessToken:"+access_token);
//        boolean flag = consumerTokenServices.revokeToken(access_token);
//        if(flag){
//            System.out.println("退出成功");
//            return JSON.toJSONString("退出成功!");
//        }
//        System.out.println("退出失败");
//        return JSON.toJSONString("退出失败!");
//    }
}
