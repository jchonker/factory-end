package com.factory.end.config;

import com.alibaba.fastjson.JSON;
import com.factory.end.util.http.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author jchonker
 * @Date 2020/8/23 0:44
 * @Version 1.0
 * 权限不足 403
 */
@Configuration
public class AccessDeniedHandlerConfig implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        PrintWriter out = httpServletResponse.getWriter();
//        out.write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员!\"}");
//        out.flush();
//        out.close();

        System.out.println("403处理...");
//        JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
        Result result = new Result(HttpStatusEnum.FORBIDDEN);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
