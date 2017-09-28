package com.cc.dc.api;

import com.cc.dc.common.http.BaseResponseInterceptor;
import com.cc.dc.common.http.converter.FastJsonConverter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by dc on 2017/9/21.
 * Api初始处理
 */
public class ApiHelper {

    private final String BASE_URL = "http://capi.douyucdn.cn/api/";

    private final String BASE_URL2 = "https://apiv2.douyucdn.cn/";

    /**
     * 超时时间 默认为15s
     */
    private final int DEFAULT_TIME_OUT = 15;

    private Retrofit retrofit;

    private Retrofit retrofit2;

    private ApiHelper() {
        if (null == retrofit) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
            builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
            builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);

            //设置拦截器
            builder.addInterceptor(new BaseResponseInterceptor());
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(FastJsonConverter.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    public static Retrofit getInstance() {
        return new ApiHelper().retrofit;
    }

    private ApiHelper(String tag) {
        if (null == retrofit2) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
            builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
            builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);

            //设置拦截器
            builder.addInterceptor(new BaseResponseInterceptor());
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            OkHttpClient okHttpClient = builder.build();
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .client(okHttpClient)
                    .addConverterFactory(FastJsonConverter.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    public static Retrofit getInstanceApiV2() {
        return new ApiHelper("v2").retrofit2;
    }
}
