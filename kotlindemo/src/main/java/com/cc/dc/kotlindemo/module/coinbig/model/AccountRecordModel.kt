package com.cc.dc.kotlindemo.module.coinbig.model

import android.util.Log
import com.cc.dc.kotlindemo.module.coinbig.bean.*
import com.cc.dc.kotlindemo.net.HttpCallBack
import com.cc.dc.kotlindemo.net.NetManager
import com.cc.dc.kotlindemo.net.api.CoinBigApi
import com.cc.dc.kotlindemo.net.function.CoinBigHttpFunction
import com.cc.dc.kotlindemo.net.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by dc on 18/7/7.
 */
object AccountRecordModel {

    fun getAccountRecords(map: Map<String, String>, callBack: HttpCallBack<List<AccountRecordBean>>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .getAccountRecords(map)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<AccountRecordBean>>() {
                    override fun onNext(bean: List<AccountRecordBean>) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "AccountRecordModel>>下单成功> " + bean.size)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "AccountRecordModel>下单失败>> " + t.message)
                    }
                })
    }

    fun getAccountRecords(info: String, callBack: HttpCallBack<List<AccountRecordBean>>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .getAccountRecords(info)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<AccountRecordBean>>() {
                    override fun onNext(bean: List<AccountRecordBean>) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "AccountRecordModel>>info下单成功> " + bean.size)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "AccountRecordModel>info下单失败>> " + t.message)
                    }
                })
    }


    fun getAccountRecords(apikey: String, shortName: String,
                          recordType: String, status: String, sign: String,
                          callBack: HttpCallBack<List<AccountRecordBean>>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .getAccountRecords(apikey, shortName, recordType, status, sign)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<AccountRecordBean>>() {
                    override fun onNext(bean: List<AccountRecordBean>) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "AccountRecordModel>>apikey> " + bean.size)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "AccountRecordModel>apikey失败>> " + t.message)
                    }
                })
    }

    fun getUserTotal(apikey: String, shortName: String, sign: String,
                          callBack: HttpCallBack<UserTotalBean>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .getUserTotal(apikey, shortName, sign)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<UserTotalBean>() {
                    override fun onNext(bean: UserTotalBean) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "AccountRecordModel>>getUserTotal> "
                                + bean.total + " " + bean.free)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "AccountRecordModel>getUserTotal失败>> " + t.message)
                    }
                })
    }

    fun getTicker(symbol: String, callBack: HttpCallBack<List<TickerBean>>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .getTicker(symbol)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<TickerBean>>() {
                    override fun onNext(bean: List<TickerBean>) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "AccountRecordModel>>TickerBean> " + bean.size)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "AccountRecordModel>TickerBean下单失败>> " + t.message)
                    }
                })
    }

    fun getKline(map: Map<String, String>, callBack: HttpCallBack<List<KlineBean>>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .getKline(map)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<KlineBean>>() {
                    override fun onNext(bean: List<KlineBean>) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "AccountRecordModel>>下单成功> " + bean.size)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "AccountRecordModel>下单失败>> " + t.message)
                    }
                })
    }

    fun getOrderInfos(map: Map<String, String>, callBack: HttpCallBack<OrderJsonBean>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .getOrderInfos(map)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<OrderJsonBean>() {
                    override fun onNext(bean: OrderJsonBean) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "getOrderInfos>>下单成功> getOrderInfos")
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "getOrderInfos>下单失败>> " + t.message)
                    }
                })
    }
}