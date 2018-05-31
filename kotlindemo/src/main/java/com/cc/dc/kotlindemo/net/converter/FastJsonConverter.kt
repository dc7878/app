package com.cc.dc.kotlindemo.net.converter

import java.lang.reflect.Type

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by dc on 2017/7/7.
 * FastJson 数据转换
 */

class FastJsonConverter private constructor() : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        return FastJsonResponseBodyConverter<Type>(type)
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<Annotation>?, methodAnnotations: Array<Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return FastJsonRequestBodyConverter<Type>()
    }

    companion object {

        fun create(): FastJsonConverter {
            return FastJsonConverter()
        }
    }
}
