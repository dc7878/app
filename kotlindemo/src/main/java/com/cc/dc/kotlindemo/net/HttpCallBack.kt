package com.cc.dc.kotlindemo.net

import io.reactivex.disposables.Disposable

/**
 * Created by dc on 2017/7/10.
 * 网络请求完成的回调
 */

interface HttpCallBack<T> {

    /**
     * 开始处理
     *
     * @param disposable
     */
    fun onStart(disposable: Disposable)

    /**
     * 请求成功
     */
    fun onResult(result: T?)

    /**
     * 请求失败
     */
    fun onError(msg: String)
}
