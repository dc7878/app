package com.cc.dc.kotlindemo.net.function

import io.reactivex.functions.Function

/**
 * Created by dc on 2017/7/11.
 * 非标准数据的map处理，主要用来统一处理不同code的问题
 */

class StringHttpFunction<String> : Function<String, String> {
    @Throws(Exception::class)
    override fun apply(response: String): String {
        // TODO: 2017/7/11 统一处理不同code的问题
        return response
    }
}
