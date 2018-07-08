package com.cc.dc.kotlindemo.module.coinbig.model

import android.util.Log
import com.cc.dc.kotlindemo.module.coinbig.bean.KlineBean
import com.cc.dc.kotlindemo.module.coinbig.bean.TradeBean
import com.cc.dc.kotlindemo.net.HttpCallBack
import com.cc.dc.kotlindemo.net.NetManager
import com.cc.dc.kotlindemo.net.api.CoinBigApi
import com.cc.dc.kotlindemo.net.function.CoinBigHttpFunction
import com.cc.dc.kotlindemo.net.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by dc on 18/7/8.
 */
object CoinBigModel {

    fun trade(apikey: String, type: String, price: String,
              amount: String, symbol: String, sign: String,
                     callBack: HttpCallBack<TradeBean>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .trade(apikey, type, price, amount, symbol, sign)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<TradeBean>() {
                    override fun onNext(bean: TradeBean) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "trade>>getUserTotal> "
                                + bean.result + " " + bean.order_id)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        callBack.onError("error")
                        Log.e("AccountRecordModel", "trade>getUserTotal失败>> " + t.message)
                    }
                })
    }

    fun trade(apikey: String, type: String,amount: String,
              symbol: String, sign: String,
              callBack: HttpCallBack<TradeBean>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .trade(apikey, type, amount, symbol, sign)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<TradeBean>() {
                    override fun onNext(bean: TradeBean) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "trade>no price>getUserTotal> "
                                + bean.result + " " + bean.order_id)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "trade>no price getUserTotal失败>> " + t.message)
                    }
                })
    }

    fun getKline(symbol: String, type:String, size: String, callBack: HttpCallBack<List<KlineBean>>) {
        NetManager.create()
                .create(CoinBigApi::class.java)
                .getKline(symbol, type, size)
                .subscribeOn(Schedulers.io())
                .map(CoinBigHttpFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<KlineBean>>() {
                    override fun onNext(bean: List<KlineBean>) {
                        callBack.onResult(bean)
                        Log.e("AccountRecordModel", "getKline>no price>getUserTotal> "
                                + bean.size)
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("AccountRecordModel", "trade>no price getUserTotal失败>> " + t.message)
                    }
                })
    }
}