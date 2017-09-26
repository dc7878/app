package com.cc.dc.api.apiservice;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.common.http.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dc on 2017/9/21.
 */
public interface LiveApiService {

    @GET("v1/live?")
    Observable<BaseResponse<List<LiveBean>>> getLiveList(@Query("offset") int offset, @Query("limit") int limit);
}
