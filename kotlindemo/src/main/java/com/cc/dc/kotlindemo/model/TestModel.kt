package com.cc.dc.kotlindemo.model

import android.util.Log
import com.cc.dc.kotlindemo.KotlinApplication
import com.cc.dc.kotlindemo.bean.ChannelBean
import com.cc.dc.kotlindemo.bean.news.NewsEntity
import com.cc.dc.kotlindemo.net.HttpCallBack
import com.cc.dc.kotlindemo.net.NetManager
import com.cc.dc.kotlindemo.net.api.TestApi
import com.cc.dc.kotlindemo.net.function.HttpFunction
import com.cc.dc.kotlindemo.net.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by dc on 2018/5/31.
 * test network model
 */
object  TestModel {
    fun getChannelJson(siteId: String, callBack: HttpCallBack<List<ChannelBean>>){
        NetManager.create()
                .create(TestApi::class.java)
                .getChannelJson(siteId)
                .map(HttpFunction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: BaseObserver<List<ChannelBean>>(){
                    override fun onNext(value: List<ChannelBean>) {
                        Log.e("TestModel", "TestModel>>>onNext>>>" + value!!.size)
                        callBack.onResult(value)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("TestModel", "TestModel>>>onError>>>" + t.message)
                        callBack.onError(t.message!!)
                    }
                })
    }

    fun getNewsList(map: Map<String, String>, callBack: HttpCallBack<List<NewsEntity>>){
        NetManager.create()
                .create(TestApi::class.java)
                .getNewsList(map)
                .map(HttpFunction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(KotlinApplication.getLifecycleProvider().bindToLifecycle())
                .subscribe(object: BaseObserver<List<NewsEntity>>(){
                    override fun onNext(value: List<NewsEntity>) {
                        Log.e("TestModel", "TestModel>>>onNext>>>" + value?.size)
                        callBack.onResult(value)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("TestModel", "TestModel>>>onError>>>" + t.message)
                        callBack.onError(t.message!!)
                    }
                })
    }
}