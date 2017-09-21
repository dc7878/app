package com.cc.dc.common.http;

/**
 * Created by dc on 2017/9/21.
 * 统一的网络错误
 */
public class ApiException extends Exception {

    private int errorCode;

    private String errorMsg;

    public ApiException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
