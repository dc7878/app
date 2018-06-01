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

    private var retrofit: Retrofit? = null

    private constructor() {
        if (null ==  retrofit) {
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)

            builder.addInterceptor(BaseParamsInterceptor())
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            val okHttpClient = builder.build()
            retrofit = Retrofit.Builder()
                    .baseUrl(TEST_URL_APP)
                    .client(okHttpClient)
                    .addConverterFactory(FastJsonConverter.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }

    companion object {

        private val TEST_URL_APP = "https://"

        private val DEFAULT_TIME_OUT = 10

        fun create(): Retrofit {
            return NetManager().retrofit!!
        }
    }
}