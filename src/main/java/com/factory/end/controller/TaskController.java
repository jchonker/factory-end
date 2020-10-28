package com.factory.end.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author jchonker
 * @Date 2020/10/20 17:04
 * @Version 1.0
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {
    @GetMapping("/getTasks")
    @ResponseBody
    public String listTasks(){
        return "任务列表";
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String newTasks(){
        return "创建了一个新的任务";
    }
}
