package com.cc.dc.kotlindemo.net.api

import com.cc.dc.kotlindemo.bean.aacoin.DetailBean
import com.cc.dc.kotlindemo.bean.aacoin.UserAccountBean
import com.cc.dc.kotlindemo.net.BaseAACoinResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by dc on 18/7/1.
 */
interface AACoinApi {

    // 获取用户账户数据
    @FormUrlEncoded
    @POST("https://api.aacoin.com/v1/account/accounts")
    fun getUserInfo(@Field("accessKey") accessKey: String,
                    @Field("sign") sign: String): Observable<BaseAACoinResponse<List<UserAccountBean>>>


    // 获取市场详情
    @FormUrlEncoded
    @POST("https://api.aacoin.com/market/detail")
    fun getDetail(@FieldMap map : Map<String, String>): Observable<BaseAACoinResponse<DetailBean>>

    // 下单
    @FormUrlEncoded
    @POST("https://api.aacoin.com/v1/order/place")
    fun orderPlace(@FieldMap map : Map<String, String>):Observable<BaseAACoinResponse<String>>
}