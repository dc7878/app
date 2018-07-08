package com.cc.dc.kotlindemo.model

import android.util.Log
import com.cc.dc.kotlindemo.bean.aacoin.DetailBean
import com.cc.dc.kotlindemo.bean.aacoin.UserAccountBean
import com.cc.dc.kotlindemo.net.HttpCallBack
import com.cc.dc.kotlindemo.net.NetManager
import com.cc.dc.kotlindemo.net.api.AACoinApi
import com.cc.dc.kotlindemo.net.function.AAcoinHttpFunction
import com.cc.dc.kotlindemo.net.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by dc on 18/7/1.
 */
object AACoinModel {
    fun getUserInfo(accessKey : String, sign : String, callBack: HttpCallBack<List<UserAccountBean>>) {
        NetManager.create()
                .create(AACoinApi::class.java)
                .getUserInfo(accessKey, sign)
                .map(AAcoinHttpFunction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<UserAccountBean>>() {
                    override fun onNext(list: List<UserAccountBean>) {
                        callBack.onResult(list)
                        Log.e("TestModel", "TestModel>>size---> " + list.size)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        callBack.onError(t.message!!)
                        Log.e("TestModel", "TestModel>>> " + t.message)
                    }
                })
    }

    fun getDetail(map: Map<String, String>, callBack: HttpCallBack<DetailBean>) {
        NetManager.create()
                .create(AACoinApi::class.java)
                .getDetail(map)
                .subscribeOn(Schedulers.io())
                .map(AAcoinHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<DetailBean>() {
                    override fun onNext(bean: DetailBean) {
                        callBack.onResult(bean)
                        Log.e("TestModel", "TestModel>>> " + bean.current)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("TestModel", "TestModel>>> " + t.message)
                    }
                })
    }

    fun orderPlace(map: Map<String, String>, callBack: HttpCallBack<String>) {
        NetManager.create()
                .create(AACoinApi::class.java)
                .orderPlace(map)
                .subscribeOn(Schedulers.io())
                .map(AAcoinHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<String>() {
                    override fun onNext(bean: String) {
                        callBack.onResult(bean)
                        Log.e("TestModel", "TestModel>>下单成功> " + bean)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("TestModel", "TestModel>下单失败>> " + t.message)
                    }
                })
    }
}