package com.cc.dc.kotlindemo.net.function

import android.util.Log
import com.cc.dc.kotlindemo.net.ApiException
import com.cc.dc.kotlindemo.net.BaseAACoinResponse
import io.reactivex.functions.Function


/**
 * Created by dc on 2018/7/1.
 * 标准的返回数据需要map转换一下
 */

class AAcoinHttpFunction<T> : Function<BaseAACoinResponse<T>, T> {

    @Throws(Exception::class)
    override fun apply(baseResponse: BaseAACoinResponse<T>): T? {
        Log.e("NewsListModel", "HttpFunction>>>" + baseResponse.data!!.toString())
        if (!baseResponse.isSuccess) {
            throw ApiException(baseResponse.status, baseResponse.msg)
        }
        return baseResponse.data
    }

}
