package com.cc.dc.api;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by dc on 2017/9/29.
 */
public class BaseParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        if ("GET".equals(method)) {
            //添加公共参数
            HttpUrl httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("client_sys","android")
                    .build();
            request = request.newBuilder().url(httpUrl).build();
        } else if ("POST".equals(method)) {
            String postBodyString;
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            formBodyBuilder.add("client_sys", "android");
            postBodyString = bodyToString(request.body());
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBodyBuilder.build());
            Request.Builder requestBuilder = request.newBuilder().method(request.method(), request.body());
            if (!TextUtils.isEmpty(postBodyString)) {
                requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), postBodyString));
            }
            request = requestBuilder.build();
        }

        return chain.proceed(request);
    }

    /**
     * requestBody 转换成sting
     */
    private static String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null) {
                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "";
        }
    }
}
