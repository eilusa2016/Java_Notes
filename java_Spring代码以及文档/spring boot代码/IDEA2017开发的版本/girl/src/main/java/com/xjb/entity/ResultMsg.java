package com.xjb.entity;

import java.util.List;

/**
 * 返回的对象类型
 * @param <T>
 */
public class ResultMsg<T> {
    private String message;

    private List<T> list;

    public ResultMsg() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
