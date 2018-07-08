package com.cc.dc.kotlindemo.net

/**
 * Created by dc on 2018/7/1.
 * 标准的网络请求返回的数据结构
 */

class BaseCoinBigResponse<T> {

    var code: Int = 0

    var msg: String? = null

    var data: T? = null

    val isSuccess: Boolean
        get() = code == 0
}
