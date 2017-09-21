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
        Response response = chain.proceed(request);
        LUtil.e("Response", response.body().toString());
        return response;
    }
}
