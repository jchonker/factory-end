package com.factory.end.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author jchonker
 * @Date 2020/8/25 14:21
 * @Version 1.0
 * 退出处理类
 */
@Component
public class MyLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
//        try {
//            //aa即为前端传来的自定义跳转的url地址
//            String url = httpServletRequest.getParameter("logout");
//            httpServletResponse.sendRedirect(url);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
