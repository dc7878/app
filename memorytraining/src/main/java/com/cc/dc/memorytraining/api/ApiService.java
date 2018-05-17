package com.cc.dc.memorytraining.api;

import com.cc.dc.common.bean.TestInfo;
import com.cc.dc.memorytraining.bean.NewsBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dc on 17/10/8.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("api.php?s=/News/getNewsListCache")
    Observable<NewsBean> getNewsList(@FieldMap() Map<String, String> map);
}
