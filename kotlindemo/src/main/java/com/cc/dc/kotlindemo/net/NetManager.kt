package com.cc.dc.kotlindemo.net

import com.cc.dc.kotlindemo.net.converter.FastJsonConverter
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by dc on 2018/5/31.
 * net manager
 */
class NetManager {

    private val testUrl130 = "https://"

    private val defaultTimeOut = 10

    private var retrofit: Retrofit? = null

    private constructor() {
        if (null ==  retrofit) {
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(defaultTimeOut.toLong(), TimeUnit.SECONDS)
            builder.connectTimeout(defaultTimeOut.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(defaultTimeOut.toLong(), TimeUnit.SECONDS)

            builder.addInterceptor(BaseParamsInterceptor())
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            val okHttpClient = builder.build()
            retrofit = Retrofit.Builder()
                    .baseUrl(testUrl130)
                    .client(okHttpClient)
                    .addConverterFactory(FastJsonConverter.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }

    companion object {

        fun getInstance(): Retrofit {
            return NetManager().retrofit!!
        }
    }
}