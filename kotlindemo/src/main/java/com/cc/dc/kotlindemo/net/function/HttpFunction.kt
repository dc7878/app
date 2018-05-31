package com.cc.dc.kotlindemo.net.function

import android.util.Log

import com.cc.dc.kotlindemo.net.ApiException
import com.cc.dc.kotlindemo.net.BaseResponse

import io.reactivex.functions.Function


/**
 * Created by dc on 2017/7/7.
 * 标准的返回数据需要map转换一下
 */

class HttpFunction<T> : Function<BaseResponse<T>, T> {

    @Throws(Exception::class)
    override fun apply(baseResponse: BaseResponse<T>): T? {
        Log.e("NewsListModel", "HttpFunction>>>" + baseResponse.data!!.toString())
        if (!baseResponse.isSuccess) {
            throw ApiException(baseResponse.code, baseResponse.msg)
        }
        return baseResponse.data
    }

}
