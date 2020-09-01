package com.factory.end.controller.second;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jchonker
 * @Date 2020/8/24 17:44
 * @Version 1.0
 */
@RestController
public class HelloController {
    @GetMapping("/admin/hello")
    public String admin(){
        return "Hello admin!";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "Hello user!";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
