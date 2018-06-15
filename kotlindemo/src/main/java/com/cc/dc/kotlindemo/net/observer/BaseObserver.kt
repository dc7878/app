package com.cc.dc.kotlindemo.net.observer

import android.accounts.NetworkErrorException
import android.net.ParseException

import com.alibaba.fastjson.JSONException
import com.cc.dc.kotlindemo.net.ApiException

import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

import io.reactivex.Observer
import io.reactivex.observers.DisposableObserver

/**
 * Created by dc on 2017/7/10.
 * 主要是用来统一处理错误相关
 * 一般情况下onError、handleErrorSelf、handleError都不需要重写
 * 1.如果需要自己显示error，则需要重写handleErrorSelf 返回true，同时重写handleError显示自己的处理
 * 2.如果需要在error的时候显示、隐藏的布局，则需要重写onError或者是handleError，在里面调用CallBack的
 * onError回调
 */

abstract class BaseObserver<T> : DisposableObserver<T>() {

    private val TAG = "NewsDetailModel"

    override fun onComplete() {}

    override fun onError(t: Throwable) {
        var error = "网络异常！请检查网络"
        if (t is NetworkErrorException || t is UnknownHostException || t is ConnectException) {
            // 网络异常
        } else if (t is SocketTimeoutException || t is InterruptedIOException || t is TimeoutException) {
            // 请求超时
            error = "请求超时,请重试"
        } else if (t is JSONException || t is ParseException) {
            // 解析错误
        } else if (t is ApiException) {
            // 自定义的错误
            error = t.message!!
        } else {
            // 其他错误
        }
        if (!handleErrorSelf()) {
            // 默认处理
        } else {
            // 自己处理错误的显示问题
            handleError(error)
        }
    }

    fun handleErrorSelf(): Boolean {
        return false
    }

    fun handleError(errorMsg: String) {}
}
