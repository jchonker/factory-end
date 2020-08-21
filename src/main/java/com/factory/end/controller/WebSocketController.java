package com.factory.end.controller;

import com.factory.end.util.PushDataToClient;
import com.factory.end.util.websocket.WebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/13 22:00
 * @Version 1.0
 */
@CrossOrigin
@RestController
@Api(value = "操作websocket的controller")
public class WebSocketController {
    /**
     * 测试请求是否成功
     * @return
     */
    @ApiOperation(value = "测试请求是否成功")
    @GetMapping("index")
    @ApiResponses(value = {@ApiResponse(code = 1000,message = "成功"),
            @ApiResponse(code = 1001,message = "失败")})
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    /**
     * 跳转到websocket页面,但是后端项目中没有此页面,此接口无效
     * @return
     */
    @ApiOperation(value = "跳转到websocket页面,但是后端项目中没有此页面,此接口无效")
    @GetMapping("page")
    @ApiResponses(value = {@ApiResponse(code = 1000,message = "成功"),
            @ApiResponse(code = 1001,message = "失败")})
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    /**
     * 推送信息给指定的用户
     * 注意:不能使用post请求,post请求会失败
     * @param message
     * @param toUserId
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "推送信息给指定的用户")
    @GetMapping("/push/{toUserId}")
    @ApiResponses(value = {@ApiResponse(code = 1000,message = "成功"),
            @ApiResponse(code = 1001,message = "失败")})
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

    @Autowired
    PushDataToClient pushDataToClient;

    /**
     * 推送数据库数据到客户端
     * 注意:不能使用post请求,post请求会失败
     * @param userId
     */
    @ApiOperation(value = "推送数据库数据到客户端")
    @GetMapping("/pushToClient")
    @ApiResponses(value = {@ApiResponse(code = 1000,message = "成功"),
            @ApiResponse(code = 1001,message = "失败")})
    public void pushToClient(@RequestParam String userId){
        System.out.println("接收到的userId参数:"+userId);
        pushDataToClient.push(userId);
    }
}
