package com.cc.dc.common.http;

/**
 * Created by dc on 2017/9/21.
 * 标准的网络请求返回的数据结构
 */
public class BaseResponse<T> {

    private int error;

    private T data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return error == ConstantCode.RESPONSE_SUCCESS;
    }
}
