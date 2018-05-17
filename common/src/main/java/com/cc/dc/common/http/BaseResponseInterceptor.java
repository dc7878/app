package com.cc.dc.common.http;

import com.alibaba.fastjson.JSONObject;
import com.cc.dc.common.bean.TestBean;
import com.cc.dc.common.bean.TestInfo;
import com.cc.dc.common.utils.LUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
//        return response;

        JSONObject jsonObject = new JSONObject();

        TestInfo testInfo = new TestInfo();
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            TestBean testBean = new TestBean();
            testBean.title = "test" + i;
            testBean.type = "";
            list.add(testBean);
        }
        testInfo.list = list;

        jsonObject.put("data", testInfo);
        jsonObject.put("code", "200");

        LUtil.e("SomeTestModel", "jsonObject>>>" + jsonObject.toJSONString());

        return response.newBuilder()
                .code(200)
                .message("ok")
                .body(ResponseBody.create(MediaType.parse("json"), jsonObject.toJSONString()))
                .build();
//        return response;
    }
}
