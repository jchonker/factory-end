package com.factory.end.config.exceptionHandler;

import com.factory.end.util.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author jchonker
 * @Date 2020/8/31 17:11
 * @Version 1.0
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    Result result;

    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.info("进入全局异常处理参数校验方法:");
        logger.error(e.getMessage(), e);
        return result.Error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 所有类型异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handlerMethodMethod(Exception e){
        logger.info("所有类型异常处理方法");
        logger.error(e.getMessage(),e);
        return result.Error(e.getMessage());
    }
}
