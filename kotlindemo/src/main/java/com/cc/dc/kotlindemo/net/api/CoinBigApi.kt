package com.cc.dc.kotlindemo.net.api

import com.cc.dc.kotlindemo.module.coinbig.bean.*
import com.cc.dc.kotlindemo.net.BaseCoinBigResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by dc on 18/7/7.
 */
interface CoinBigApi {

    // 获取用户账户数据
    @POST("https://www.coinbig.com/api/publics/v1/account_records")
    fun getAccountRecords(@QueryMap map: Map<String, String>):
            Observable<BaseCoinBigResponse<List<AccountRecordBean>>>

    @POST("https://www.coinbig.com/api/publics/v1/account_records/{info}")
    fun getAccountRecords(@Path("info") info: String):
            Observable<BaseCoinBigResponse<List<AccountRecordBean>>>

    @POST("https://www.coinbig.com/api/publics/v1/account_records")
    fun getAccountRecords(@Query("apikey") apikey: String,
                          @Query("shortName") shortName: String,
                          @Query("recordType") recordType: String,
                          @Query("status") status: String,
                          @Query("sign") sign: String):Observable<BaseCoinBigResponse<List<AccountRecordBean>>>

    @POST("https://www.coinbig.com/api/publics/v1/userinfoBySymbol")
    fun getUserTotal(@Query("apikey") apikey: String,
                          @Query("shortName") shortName: String,
                          @Query("sign") sign: String):Observable<BaseCoinBigResponse<UserTotalBean>>


    @GET("https://www.coinbig.com/api/publics/v1/ticker")
    fun getTicker(@Query("symbol") symbol: String):
            Observable<BaseCoinBigResponse<List<TickerBean>>>

    @GET("https://www.coinbig.com/api/publics/v1/kline")
    fun getKline(@QueryMap map: Map<String, String>):
            Observable<BaseCoinBigResponse<List<KlineBean>>>

    @FormUrlEncoded
    @POST("https://www.coinbig.com/api/publics/v1/orders_info")
    fun getOrderInfos(@FieldMap map: Map<String, String>):
            Observable<BaseCoinBigResponse<OrderJsonBean>>


    @POST("https://www.coinbig.com/api/publics/v1/trade")
    fun trade(@Query("apikey") apikey: String,
              @Query("type") type: String,
              @Query("price") price: String,
              @Query("amount") amount: String,
              @Query("symbol") symbol: String,
              @Query("sign") sign: String): Observable<BaseCoinBigResponse<TradeBean>>

    @POST("https://www.coinbig.com/api/publics/v1/trade")
    fun trade(@Query("apikey") apikey: String,
              @Query("type") type: String,
              @Query("amount") amount: String,
              @Query("symbol") symbol: String,
              @Query("sign") sign: String): Observable<BaseCoinBigResponse<TradeBean>>

    @GET("https://www.coinbig.com/api/publics/v1/kline")
    fun getKline(@Query("symbol") symbol: String,@Query("type") type: String
                 ,@Query("size") size: String):
            Observable<BaseCoinBigResponse<List<KlineBean>>>
}