package com.cc.dc.kotlindemo.net.converter

import com.alibaba.fastjson.JSON

import java.io.IOException
import java.lang.reflect.Type

import okhttp3.ResponseBody
import okio.BufferedSource
import okio.Okio
import retrofit2.Converter

/**
 * Created by dc on 2017/7/7.
 * FastJson返回值转换
 */

class FastJsonResponseBodyConverter<T> : Converter<ResponseBody, T> {

    private var type: Type? = null

    constructor(type: Type?) {
        this.type = type
    }

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val bufferedSource = Okio.buffer(value.source())
        val tempStr = bufferedSource.readUtf8()
        bufferedSource.close()
        return JSON.parseObject(tempStr, type)
    }
}
