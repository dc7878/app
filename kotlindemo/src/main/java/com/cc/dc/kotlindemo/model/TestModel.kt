package com.cc.dc.kotlindemo.model

import android.util.Log
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.cc.dc.kotlindemo.net.HttpCallBack
import com.cc.dc.kotlindemo.net.NetManager
import com.cc.dc.kotlindemo.net.api.TestApi
import com.cc.dc.kotlindemo.net.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by dc on 2018/5/31.
 */
object  TestModel {

    fun getChannelJson(siteId: String, callBack: HttpCallBack<String>){
        NetManager.getInstance()
                .create(TestApi::class.java)
                .getChannelJson(siteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: BaseObserver<String>(){
                    override fun onSubscribe(d: Disposable?) {
                        callBack.onStart(d!!)
                        Log.e("TestModel", "TestModel>>>onSubscribe>>>")
                    }

                    override fun onNext(value: String?) {
                        callBack.onResult(value!!)
                        Log.e("TestModel", "TestModel>>>onNext>>>" + value)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("TestModel", "TestModel>>>onError>>>" + t.message)
                        callBack.onError(t.message!!)
                    }
                })
    }
}