package com.factory.end.controller.second;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author jchonker
 * @Date 2020/8/21 10:09
 * @Version 1.0
 */
@Controller
public class IndexController {
    /**
     * 跳转首页
     * @param response
     */
    @GetMapping("")
    public void index1(HttpServletResponse response){
        //内部重定向
        try {
            System.out.println("---1---");
            response.sendRedirect("/index");
        }catch (IOException e){
            e.printStackTrace();;
        }
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        System.out.println("---2---");
        return "index";
    }
}
