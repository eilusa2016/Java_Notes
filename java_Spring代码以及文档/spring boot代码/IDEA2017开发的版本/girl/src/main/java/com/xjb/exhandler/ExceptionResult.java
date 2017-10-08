package com.xjb.exhandler;

/**
 * 自定义异常信息类
 * 这个类避不能继承任何异常类
 */
public class ExceptionResult {
    //业务类型
    private String bizType;
    //业务代码
    private int bizCode;
    //错误信息
    private String message;

    public ExceptionResult() {

    }

    public ExceptionResult(String bizType, int bizCode, String message) {
        this.bizType = bizType;
        this.bizCode = bizCode;
        this.message = message;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public int getBizCode() {
        return bizCode;
    }

    public void setBizCode(int bizCode) {
        this.bizCode = bizCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
