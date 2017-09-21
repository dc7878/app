package com.cc.dc.api.apiservice;

import com.cc.dc.bean.HomeCateBean;
import com.cc.dc.common.http.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by dc on 2017/9/21.
 */
public interface HomeApiService {

    @GET("getCateList")
    Observable<BaseResponse<List<HomeCateBean>>> getCateList();
}
