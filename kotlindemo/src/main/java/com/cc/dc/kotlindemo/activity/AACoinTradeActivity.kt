package com.cc.dc.kotlindemo.activity

import android.util.Log
import butterknife.OnClick
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.base.BaseActivity
import com.cc.dc.kotlindemo.bean.aacoin.DetailBean
import com.cc.dc.kotlindemo.bean.aacoin.UserAccountBean
import com.cc.dc.kotlindemo.model.AACoinModel
import com.cc.dc.kotlindemo.net.HttpCallBack
import com.cc.dc.kotlindemo.utils.StringUtil
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_aacoin.*
import java.math.BigDecimal
import java.util.*


/**
 * Created by dc on 18/7/1.
 */
class AACoinTradeActivity : BaseActivity() {

    private lateinit var current : String

    override fun getLayout(): Int = R.layout.activity_aacoin

    val accessKey = ""
    val secretKey = ""

//    val actualSignature = StringUtil.encodeHmacSHA256(paramString, secretKey)

    var count: Int = 0

    private var startTrade : Boolean = false

    private var stopTrade : Boolean = true

    override fun initView() {
        Log.e("TestModel", "initView")

        tvUserHave.text = resources.getString(R.string.user_have, "111")


        getUserAccounts()
        getDetail()
    }

    fun getUserAccounts() {
        val paramString = "accessKey=" + accessKey

        val actualSignature = StringUtil.encodeHmacSHA256(paramString, secretKey)

        AACoinModel.getUserInfo(accessKey, actualSignature, object : HttpCallBack<List<UserAccountBean>> {
            override fun onStart(disposable: Disposable) {
            }

            override fun onResult(result: List<UserAccountBean>?) {
                var accounts : String = "账户余额:\n"
                for(item in result!!) {
                    Log.e("TestModel", "TestModel>>>" + item.currencyCode + "---"
                            + item.accounts[0].balance)
                    when(item.currencyCode) {
                        "BTC" -> {
                            accounts += "BTC: " + item.accounts[0].balance + "\n"
                        }
                        "ETH" -> {
                            accounts += "ETH: " + item.accounts[0].balance + "\n"
                        }
                        "USDT" -> {
                            accounts += "USDT: " + item.accounts[0].balance
                        }
                    }
                }
                tvUserHave.text = accounts
            }

            override fun onError(msg: String) {
            }
        })
    }

    fun getDetail() {
        val map = HashMap<String, String>()
        map.put("symbol", "AAT_ETH")

        AACoinModel.getDetail(map, object : HttpCallBack<DetailBean> {
            override fun onStart(disposable: Disposable) {
            }

            override fun onResult(result: DetailBean?) {
                tvHeight.text = resources.getString(R.string.detail_highest, result!!.highest)

                tvLow.text =  resources.getString(R.string.detail_lowest, result!!.lowest)

                tvCurrent.text =  resources.getString(R.string.detail_current, result!!.current)


                if (startTrade && !stopTrade) {
                    if (count % 2 == 0) {
                        current = result!!.current
                        orderPlace(current)
                        Log.e("TestModel", "-买单-" + current)
                    } else {
                        orderPlace(current)
                    }
                }
            }

            override fun onError(msg: String) {
            }
        })
    }

    fun orderPlace(price : String) {
        var paramsString: String? = ""

        val map = HashMap<String, String>()
        map.put("accessKey", accessKey)
        map.put("symbol", "AAT_ETH")
        if (count % 2 == 0) {
            // 买
            map.put("type", "buy-limit")
            map.put("price", price)
        } else {
            // 卖
            val currentPrice = price.toDouble() * (1 + 0.007)
            map.put("price", round(currentPrice, 7).toString())
            map.put("type", "sell-limit")

            Log.e("TestModel", "-卖单-" + round(currentPrice, 7).toString())

        }
        map.put("quantity", "10")

        var sortedMap = map.toSortedMap()

        for((k, v) in sortedMap) {
            paramsString += k + "=" + v + "&"

        }
        paramsString = paramsString!!.substring(0, paramsString!!.length - 1)

        val actualSignature = StringUtil.encodeHmacSHA256(paramsString, secretKey)

        map.put("sign", actualSignature)

        AACoinModel.orderPlace(map, object : HttpCallBack<String> {
            override fun onStart(disposable: Disposable) {
            }

            override fun onResult(result: String?) {
                Thread.sleep(300)
                count ++
                getDetail()
            }

            override fun onError(msg: String) {
            }
        })
    }


    fun round(v: Double?, scale: Int): Double {
        if (scale < 0) {
            throw IllegalArgumentException("The scale must be a positive integer or zero")
        }
        val b = if (null == v) BigDecimal("0.0") else BigDecimal(java.lang.Double.toString(v))
        val one = BigDecimal("1")
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toDouble()
    }


    @OnClick(R.id.getUserAccount)
    fun clickGetUserAccount() {
        getUserAccounts()
    }

    @OnClick(R.id.getCurrent)
    fun clickGetCurrent() {
        startTrade = false
        stopTrade = true
        getDetail()
    }

    @OnClick(R.id.startTrade)
    fun clickStartTrade() {
        startTrade = true
        stopTrade = false
        getDetail()
    }

    @OnClick(R.id.stopTrade)
    fun clickStopTrade() {
        startTrade = false
        stopTrade = true
    }
}