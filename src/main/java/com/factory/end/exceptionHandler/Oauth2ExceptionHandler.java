package com.factory.end.exceptionHandler;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author jchonker
 * @Date 2020/8/26 10:10
 * @Version 1.0
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {
    /**
     * OAuth2全局异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public String handleOauth2(OAuth2Exception e){
        System.out.println("发生OAuth2Exception:"+e);
        return e.toString();
    }
}
