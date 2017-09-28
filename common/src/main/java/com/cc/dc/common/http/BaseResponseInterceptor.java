package com.cc.dc.common.http;

import com.cc.dc.common.utils.LUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dc on 2017/9/21.
 */
public class BaseResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//
        LUtil.e("BaseResponseInterceptor", "BaseResponseInterceptor>>>" + request.url().toString());
//
//        String name = request.header("Test");
//        String realUrl = "http://www.learn2sleep.com";
//        HttpUrl httpUrl = HttpUrl.parse(realUrl);
//
//        HttpUrl otherHttpUrl = request.url();
//
//        HttpUrl url = otherHttpUrl.newBuilder().scheme(httpUrl.scheme()).host(httpUrl.host()).port(httpUrl.port()).build();
//
//
//
//        Request.Builder newBuilder = request.newBuilder();
//        request = newBuilder.url(url).build();
//        LUtil.e("BaseResponseInterceptor", "BaseResponseInterceptor>>>" + request.url().toString());

        Response response = chain.proceed(request);
        LUtil.e("Response", response.body().toString());
        return response;
    }
}
