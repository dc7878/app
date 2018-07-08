package com.cc.dc.kotlindemo.module.coinbig.activity

import android.util.Log
import butterknife.OnClick
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.base.BaseActivity
import com.cc.dc.kotlindemo.module.coinbig.bean.KlineBean
import com.cc.dc.kotlindemo.module.coinbig.bean.TradeBean
import com.cc.dc.kotlindemo.module.coinbig.bean.UserTotalBean
import com.cc.dc.kotlindemo.module.coinbig.model.AccountRecordModel
import com.cc.dc.kotlindemo.module.coinbig.model.CoinBigModel
import com.cc.dc.kotlindemo.net.HttpCallBack
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_coin_big.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by dc on 18/7/7.
 */
class CoinBigTradeActivity : BaseActivity() {

    private val apikey = ""

    private val secretKey = ""

    private var isBuy = true

    private var isStopTrade = false

    private var count = 0

    private var everyTimeMin = 2

    private var everyTimeCount = 0.0001f

    override fun getLayout(): Int = R.layout.activity_coin_big

    override fun initView() {
    }

    private fun getKline() {
        val symbol = "ETH_USDT"
        val type = "1min"
        val size = "1"

        CoinBigModel.getKline(symbol, type,size,
                object : HttpCallBack<List<KlineBean>> {
                    override fun onStart(disposable: Disposable) {
                    }

                    override fun onResult(result: List<KlineBean>?) {
                        Log.e("AccountRecordModel", ">>" + result!![0].last)
                        val price = result!![0].last
                        trade(price)
                    }

                    override fun onError(msg: String) {
                        isStopTrade = true
                        updateTradeStatus(false)
                    }
                })
    }

    fun trade(priceInfo: String) {
        if (isStopTrade) {
            return
        }
        val map = HashMap<String, String>()

        val key = apikey
        var type = "buy"
        if (isBuy) {
            type = "buy"
        } else {
            type = "sell"
        }
        val price = priceInfo
        val amount = (everyTimeCount * everyTimeMin).toString()
        val symbol = "eth_usdt"

        map.put("apikey", key)
        map.put("type", type)
        map.put("price", price)
        map.put("amount", amount)
        map.put("symbol", symbol)

        CoinBigModel.trade(key, type, price, amount, symbol, getSign(map),
                object : HttpCallBack<TradeBean> {
                    override fun onStart(disposable: Disposable) {
                    }

                    override fun onResult(result: TradeBean?) {
                        updateTradeStatus(true)
                        Thread.sleep(3000)
                        count ++
                        Log.e("NewsListModel", "NewsListModel>>" + count)
                        if (count < 6) {
                            trade(priceInfo)
                        } else {
                            count = 0
                            isBuy = !isBuy
                            getKline()
                        }
                    }

                    override fun onError(msg: String) {
                        isStopTrade = true
                        updateTradeStatus(false)
                    }
                })
    }

    fun getTotal(shortName: String) {
        val map = HashMap<String, String>()
        map.put("apikey", apikey)
        map.put("shortName", shortName)

        AccountRecordModel.getUserTotal(apikey, shortName, getSign(map), object : HttpCallBack<UserTotalBean> {
            override fun onStart(disposable: Disposable) {
            }

            override fun onResult(result: UserTotalBean?) {
                if (shortName == "eth") {
                    tvETH.text = "eth: " + result!!.free
                } else if (shortName == "USDT"){
                    tvUSDT.text = "USDT: " + result!!.total
                }
            }

            override fun onError(msg: String) {
            }
        })
    }

    fun encryption(plainText: String): String {
        var re_md5 = String()
        try {
            val md = MessageDigest.getInstance("MD5")
            md.update(plainText.toByteArray())
            val b = md.digest()

            var i: Int

            val buf = StringBuffer("")
            for (offset in b.indices) {
                i = b[offset].toInt()
                if (i < 0)
                    i += 256
                if (i < 16)
                    buf.append("0")
                buf.append(Integer.toHexString(i))
            }

            re_md5 = buf.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return re_md5
    }

    fun getSign(map: Map<String, String>): String {
        var paramsString = ""
        var sortedMap = map.toSortedMap()

        for((k, v) in sortedMap) {
            paramsString += k + "=" + v + "&"

        }
        paramsString += "secret_key=" + secretKey
        return encryption(paramsString!!).toUpperCase()
    }

    private fun updateTradeStatus(status: Boolean) {
        if (status) {
            tvStatus.text = "交易中..."
        } else {
            tvStatus.text = "交易停止..."

        }
    }

    @OnClick(R.id.btnGetUserETH)
    fun clickGetUserAccountETH() {
        getTotal("eth")
    }

    @OnClick(R.id.btnGetUserUSDT)
    fun clickGetUserAccountUSDT() {
        getTotal("USDT")
    }

    @OnClick(R.id.startBuyTrade)
    fun clickStartBuyTrade() {
        count = 0
        isBuy = true
        isStopTrade = false
        getKline()
    }

    @OnClick(R.id.startSellTrade)
    fun clickStartSellTrade() {
        count = 0
        isBuy = false
        isStopTrade = false
        getKline()
    }

    @OnClick(R.id.stopTrade)
    fun clickStopTrade() {
        count = 0
        isBuy = true
        isStopTrade = true
        updateTradeStatus(false)
    }

    @OnClick(R.id.tvCount1)
    fun cliclTvCount1() {
        everyTimeCount = 0.01f
        updateEveryCount()
    }

    @OnClick(R.id.tvCount2)
    fun cliclTvCount2() {
        everyTimeCount = 0.001f
        updateEveryCount()
    }

    @OnClick(R.id.tvCount3)
    fun cliclTvCount3() {
        everyTimeCount = 0.0001f
        updateEveryCount()
    }

    private fun updateEveryCount() {
        tvCount.text = "每次交易量: " + (everyTimeCount * everyTimeMin)
    }
}