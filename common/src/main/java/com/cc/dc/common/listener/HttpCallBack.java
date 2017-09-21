package com.cc.dc.common.listener;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2017/9/21.
 * 网络请求完成的回调
 */
public interface HttpCallBack<T> {

    /**
     * 开始处理
     * @param disposable
     */
    void onStart(Disposable disposable);

    /**
     * 请求成功
     */
    void onResult(T result);

    /**
     * 请求失败
     */
    void onError();
}
