package com.xjb.exhandler;





/**
 * 自定义的异常类
 */
public class PersonException  extends  Exception {

    private int code;//这个code应该用枚举类型的比较合适
    private String msg;

    public PersonException(String message, int code) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
