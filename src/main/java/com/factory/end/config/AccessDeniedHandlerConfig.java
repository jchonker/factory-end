package com.factory.end.config;

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
 */
//@Configuration
public class AccessDeniedHandlerConfig implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        PrintWriter out = httpServletResponse.getWriter();
//        out.write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员!\"}");
//        out.flush();
//        out.close();

//        // Put exception into request scope (perhaps of use to a view)
//        httpServletRequest.setAttribute(WebAttributes.ACCESS_DENIED_403, e);
//
//        // Set the 403 status code.
//        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//
//        // forward to error page.
//        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/login");
//        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
