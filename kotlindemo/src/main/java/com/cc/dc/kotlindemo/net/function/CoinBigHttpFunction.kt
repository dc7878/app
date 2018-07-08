package com.cc.dc.kotlindemo.net.function

import android.util.Log
import com.cc.dc.kotlindemo.net.ApiException
import com.cc.dc.kotlindemo.net.BaseCoinBigResponse
import io.reactivex.functions.Function


/**
 * Created by dc on 2018/7/1.
 * 标准的返回数据需要map转换一下
 */

class CoinBigHttpFunction<T> : Function<BaseCoinBigResponse<T>, T> {

    @Throws(Exception::class)
    override fun apply(baseResponse: BaseCoinBigResponse<T>): T? {
        Log.e("NewsListModel", "HttpFunction>>>" + baseResponse.data!!.toString())
        if (!baseResponse.isSuccess) {
            Log.e("AccountRecordModel", "AccountRecordModel>>>" + baseResponse.code)
            throw ApiException(baseResponse.code, baseResponse.msg)
        }
        return baseResponse.data
    }

}
