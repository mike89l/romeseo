package com.ruoyi.system.domain;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    private int code;        // 状态码
    private String message;  // 消息
    private T data;         // 数据

    public ResponseData(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters 和 Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}