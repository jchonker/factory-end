package com.factory.end.config;

import com.alibaba.fastjson.JSON;
import com.factory.end.exception.UserNotLoginException;
import com.factory.end.util.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author jchonker
 * @Date 2020/10/19 16:25
 * @Version 1.0
 * 用户未登录获取登录状态过期失效
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        Result result = new Result(HttpStatusEnum.UNAUTHORIZED.code()+"","未登录","");
//        Result result = new Result();
//        httpServletResponse.setContentType("text/json;charset=utf-8");
//        httpServletResponse.getWriter().write(JSON.toJSONString(result.Enum(HttpStatusEnum.UNAUTHORIZED)));
        JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
