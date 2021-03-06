package com.cc.dc.kotlindemo.net.api

import com.cc.dc.kotlindemo.bean.ChannelBean
import com.cc.dc.kotlindemo.bean.news.NewsEntity
import com.cc.dc.kotlindemo.net.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by dc on 2018/5/31.
 */
interface TestApi {


    @FormUrlEncoded
    @POST("https://api.aacoin.com/v1/account/accounts")
    fun getUserInfo(@Field("accessKey") accessKey: String, @Field("sign") sign: String): Observable<String>


    @FormUrlEncoded
    @POST("api.php?s=/Type/getTypeListCache")
    fun getChannelJson(@Field("siteid") siteId: String): Observable<BaseResponse<List<ChannelBean>>>

    @FormUrlEncoded
    @POST("api.php?s=/News/getNewsListCache")
    fun getNewsList(@FieldMap map: Map<String, String>): Observable<BaseResponse<List<NewsEntity>>>
}