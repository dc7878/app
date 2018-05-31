package com.cc.dc.kotlindemo.net.api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by dc on 2018/5/31.
 */
interface TestApi {

    @FormUrlEncoded
    @POST("api.php?s=/Type/getTypeListCache")
    fun getChannelJson(@Field("siteid") siteId: String): Observable<String>
}