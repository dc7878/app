package com.cc.dc.kotlindemo.net.converter

import com.alibaba.fastjson.JSON

import java.io.IOException

import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter

/**
 * Created by dc on 2017/7/7.
 * FastJson请求体转换
 */

class FastJsonRequestBodyConverter<T> : Converter<T, RequestBody> {

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value))
    }

    companion object {

        private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
    }
}
