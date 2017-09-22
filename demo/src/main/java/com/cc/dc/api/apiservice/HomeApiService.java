package com.cc.dc.api.apiservice;

import com.cc.dc.bean.HomeCateBean;
import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.http.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by dc on 2017/9/21.
 */
public interface HomeApiService {

    @GET("homeCate/getCateList")
    Observable<BaseResponse<List<HomeCateBean>>> getCateList();

    @GET("v1/slide/6")
    Observable<BaseResponse<List<HomeRecommendSliderBean>>> getHomeSlides();
}
