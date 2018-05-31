package com.cc.dc.kotlindemo.net

/**
 * Created by dc on 2017/7/7.
 * 标准的网络请求返回的数据结构
 */

class BaseResponse<T> {
    var code: Int = 0

    var msg: String? = null

    var data: T? = null

    val isSuccess: Boolean
        get() = code == ConstantCode.RESPONSE_SUCCESS || code == 209
}
