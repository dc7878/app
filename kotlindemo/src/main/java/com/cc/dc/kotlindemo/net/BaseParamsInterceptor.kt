package com.cc.dc.kotlindemo.net

import android.os.Build
import android.text.TextUtils

import java.io.IOException

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer

/**
 * Created by dc on 2017/7/7.
 * 添加公共的参数信息
 * 目前只处理了POST请求的
 */

class BaseParamsInterceptor : Interceptor {

    private//                userAgent = WebSettings.getDefaultUserAgent(KotlinApplication);
    val userAgent: String
        get() {
            var userAgent = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                } catch (e: Exception) {
                    userAgent = System.getProperty("http.agent")
                }

            } else {
                userAgent = System.getProperty("http.agent")
            }
            val sb = StringBuffer()
            var i = 0
            val length = userAgent.length
            while (i < length) {
                val c = userAgent[i]
                if (c <= '\u001f' || c >= '\u007f') {
                    sb.append(String.format("\\u%04x", c.toInt()))
                } else {
                    sb.append(c)
                }
                i++
            }
            return sb.toString()
        }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var postBodyString = ""

        if (request.method() == "POST") {
            val formBodyBuilder = FormBody.Builder()

            formBodyBuilder.add("platform", "Android")
            formBodyBuilder.add("system_version", Build.MODEL + "/Android " + Build.VERSION.RELEASE.toString())
            //            UserBean userBean = SPUtil.create().getUser();
            //            if (null != userBean && !TextUtils.isEmpty(userBean.getLast_login_token())) {
            //                formBodyBuilder.add("login_token", userBean.getLast_login_token());
            //            }
            //            if (null != userBean && !TextUtils.isEmpty(userBean.getUid())) {
            //                formBodyBuilder.add("app_uid", userBean.getUid());
            //                RequestBody body = request.body();
            //                // 如果参数本身有uid这个参数，那么则不加通用的uid参数
            //                if (body != null && !TextUtils.isEmpty(bodyToString(body))) {
            //                    if (!bodyToString(request.body()).contains("uid")) {
            //                        formBodyBuilder.add("uid", userBean.getUid());
            //                    }
            //                }
            //            }
            //            String versionName;
            //            try {
            //                versionName = DeviceInfoUtil.getVersionName(App.create());
            //                if (!TextUtils.isEmpty(versionName)) {
            //                    formBodyBuilder.add("version", versionName);
            //                }
            //            } catch (PackageManager.NameNotFoundException e) {
            //                e.printStackTrace();
            //            }
            postBodyString = bodyToString(request.body())
            postBodyString += (if (postBodyString.length > 0) "&" else "") + bodyToString(formBodyBuilder.build())
        }

        val requestBuilder = request.newBuilder().removeHeader("User-Agent").addHeader("User-Agent",
                userAgent)
                .method(request.method(), request.body())
        if (!TextUtils.isEmpty(postBodyString)) {
            requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), postBodyString))
        }
        return chain.proceed(requestBuilder.build())
    }

    /**
     * requestBody 转换成sting
     */
    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null) {
                request.writeTo(buffer)
            } else {
                return ""
            }
            return buffer.readUtf8()
        } catch (e: IOException) {
            return ""
        }

    }
}
