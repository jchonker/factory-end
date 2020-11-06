package com.factory.end.util.http;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author jchonker
 * @Date 2020/8/27 17:53
 * @Version 1.0
 * Web接口统一返回结果
 */
@Component
public class Result implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    private String code;
    /** 状态描述 */
    private String message;
    /** 业务数据 */
    private Object data;

    /** 增加状态枚举类 */
    private HttpStatusEnum httpStatusEnum;


    /**用户自定义返回错误CODE*/
    public static String ERROR_CODE = "-1";

    public static String SUCCESS_CODE = "200";
    private String SUCCESS_MSG = "操作成功";
    /** 构造方法 */
    public Result() {
        super();
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MSG;
    }

    public Result(Object data) {
        super();
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MSG;
        this.data = data;
    }

    /**
     * 构造方法
     * @param code		状态码
     * @param message	状态描述
     */
    public Result(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }


    public Result(String code, String message,String data) {
        super();
        this.code = code;
        this.message = message;
        this.data=data;
    }

    /**
     * 构造方法
     * @param code		状态码
     * @param message	状态描述
     * @param data		业务数据
     */
    public Result(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造方法
     * @param httpStatusEnum
     */
    public Result(HttpStatusEnum httpStatusEnum){
        this.httpStatusEnum = httpStatusEnum;
        this.code = httpStatusEnum.code()+"";
        this.message = httpStatusEnum.reasonPhraseCN();
    }

    /**
     * 构造方法
     * @param httpStatusEnum   状态枚举类
     * @param data  业务数据
     */
    public Result(HttpStatusEnum httpStatusEnum,Object data){
        this.httpStatusEnum = httpStatusEnum;
        this.code = httpStatusEnum.code()+"";
        this.message = httpStatusEnum.reasonPhraseCN();
        this.data = data;
    }

    /**
     * 返回http状态
     * @param httpStatusEnum
     * @return
     */
    public Result Enum(HttpStatusEnum httpStatusEnum){
        return new Result(httpStatusEnum.code()+"",httpStatusEnum.reasonPhraseUS(),httpStatusEnum.reasonPhraseCN());
    }

    /**
     * 带业务数据的成功返回数据
     * @param data
     * @return
     */
    public Result Success(Object data){
        this.httpStatusEnum = HttpStatusEnum.OK;
        return new Result(httpStatusEnum.code()+"",httpStatusEnum.reasonPhraseCN(),data);
    }

    /**
     * 不带业务数据的成功返回
     * @return
     */
    public Result Success(){
        this.httpStatusEnum = HttpStatusEnum.OK;
        return new Result(httpStatusEnum.code()+httpStatusEnum.reasonPhraseCN());
    }

    /**
     * 带业务数据的失败返回
     * @param data
     * @return
     */
    public Result Fail(Object data){
        this.httpStatusEnum = HttpStatusEnum.BAD_REQUEST;
        return new Result(httpStatusEnum.code()+"",httpStatusEnum.reasonPhraseCN(),data);
    }

    /**
     * 不带业务数据的请求失败
     * @return
     */
    public Result Fail(){
        this.httpStatusEnum = HttpStatusEnum.BAD_REQUEST;
        return new Result(httpStatusEnum.code()+"",httpStatusEnum.reasonPhraseCN());
    }

    /**
     * 带业务数据的错误返回
     * @param data
     * @return
     */
    public Result Error(Object data){
        this.httpStatusEnum = HttpStatusEnum.INTERNAL_SERVER_ERROR;
        return new Result(httpStatusEnum.code()+"",httpStatusEnum.reasonPhraseCN(),data);
    }

    /**
     * 不带业务数据的服务器错误
     * @return
     */
    public Result Error(){
        this.httpStatusEnum = HttpStatusEnum.INTERNAL_SERVER_ERROR;
        return new Result(httpStatusEnum.code()+"",httpStatusEnum.reasonPhraseCN());
    }


    /** 状态码 */
    public String getCode() {
        return code;
    }

    /** 状态码 */
    public void setCode(String code) {
        this.code = code;
    }

    /** 状态描述 */
    public String getMessage() {
        return message;
    }

    /** 状态描述 */
    public void setMessage(String message) {
        this.message = message;
    }

    /** 业务数据 */
    public Object getData() {
        return data;
    }

    /** 业务数据 */
    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return SUCCESS_CODE.equals(code);
    }
}
