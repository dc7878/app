package com.cc.dc.kotlindemo.net.converter

import android.util.Log
import com.alibaba.fastjson.JSON
import okhttp3.ResponseBody
import okio.Okio
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

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
        Log.e("NewsListModel", "" + tempStr)
        bufferedSource.close()
        return JSON.parseObject(tempStr, type)
    }
}
