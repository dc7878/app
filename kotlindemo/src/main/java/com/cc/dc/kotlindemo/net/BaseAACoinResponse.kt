package com.cc.dc.kotlindemo.net

/**
 * Created by dc on 2018/7/1.
 * 标准的网络请求返回的数据结构
 */

class BaseAACoinResponse<T> {
    var status: Int = 0

    var msg: String? = null

    var data: T? = null

    val isSuccess: Boolean
        get() = status == 1000
}
