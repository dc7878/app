package com.cc.dc.common.http.observer;

import android.accounts.NetworkErrorException;
import android.net.ParseException;

import com.alibaba.fastjson.JSONException;
import com.cc.dc.common.http.ApiException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;

/**
 * Created by dc on 2017/9/21.
 * 主要是用来统一处理错误相关
 * 一般情况下onError、handleErrorSelf、handleError都不需要重写
 * 1.如果需要自己显示error，则需要重写handleErrorSelf 返回true，同时重写handleError显示自己的处理
 * 2.如果需要在error的时候显示、隐藏的布局，则需要重写onError或者是handleError，在里面调用CallBack的
 *   onError回调
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private final String TAG = "BaseObserver";

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable t) {
        String error = "网络异常,请检查网络连接";
        if (t instanceof NetworkErrorException || t instanceof UnknownHostException || t instanceof ConnectException) {
            // 网络异常
        } else if (t instanceof SocketTimeoutException || t instanceof InterruptedIOException || t instanceof TimeoutException) {
            // 请求超时
            error = "请求超时,请重试";
        } else if (t instanceof JSONException
                || t instanceof ParseException) {
            // 解析错误
        } else if (t instanceof ApiException) {
            // 自定义的错误
            ApiException exception = (ApiException) t;
            error = exception.getMessage();
        } else {
            // 其他错误
        }
        if (!handleErrorSelf()) {
            // 默认处理
        } else {
            // 自己处理错误的显示问题
            handleError(error);
        }
    }

    public boolean handleErrorSelf() {
        return false;
    }

    public void handleError(String errorMsg) {
    }
}
