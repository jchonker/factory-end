package com.factory.end.exception;

import org.springframework.stereotype.Component;

/**
 * @Author jchonker
 * @Date 2020/10/19 16:33
 * @Version 1.0
 * 用户未登录异常
 */
@Component
public class UserNotLoginException extends RuntimeException{
    public UserNotLoginException(){
        super("用户未登录");
    }
}
