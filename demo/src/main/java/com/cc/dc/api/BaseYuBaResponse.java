package com.cc.dc.api;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseYuBaResponse<T> {

    @JSONField(name = "status_code")
    private int statusCode;

    private T data;

    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return statusCode == 200;
    }
}
