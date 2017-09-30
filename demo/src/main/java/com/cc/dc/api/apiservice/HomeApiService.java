package com.cc.dc.api.apiservice;

import com.cc.dc.bean.HomeCateBean;
import com.cc.dc.bean.HomeCateDataBean;
import com.cc.dc.bean.HomeCateListBean;
import com.cc.dc.bean.HomeHotDataBean;
import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.http.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dc on 2017/9/21.
 */
public interface HomeApiService {

    @GET("homeCate/getCateList")
    Observable<BaseResponse<List<HomeCateBean>>> getCateList();

    @GET("v1/slide/6")
    Observable<BaseResponse<List<HomeRecommendSliderBean>>> getHomeSlides();

    @GET("v1/getbigDataRoom")
    Observable<BaseResponse<List<HomeCateListBean>>> getBigDataRoomList(@Query("aid") String aid, @Query("time") String time, @Query("auth") String auth);

    @GET("live/home/custom")
    Observable<BaseResponse<List<HomeCateDataBean>>> getHomeCustomList();

    @GET("v1/getHotCate")
    Observable<BaseResponse<List<HomeHotDataBean>>> getHomeHotList(@Query("aid") String aid, @Query("time") String time, @Query("auth") String auth);
}
