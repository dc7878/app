package com.cc.dc.api.apiservice;

import com.cc.dc.api.BaseYuBaResponse;
import com.cc.dc.bean.find.FindFeedInfo;
import com.cc.dc.bean.find.TopicMessageBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by dc on 2017/9/21.
 * URL:https://mapi-yuba.douyu.com/wb/v3
 */
public interface FindApiService {

    /**
     * 四个圆圈下面的三条数据
     */
    @GET("topic/recom")
    Observable<BaseYuBaResponse<List<TopicMessageBean>>> getTopicMessage(@Header("client") String header);

    /**
     * 精选
     * @param page
     */
    @GET("digest")
    Observable<BaseYuBaResponse<FindFeedInfo>> getDigestList(@Header("client") String header, @Query("page") int page);

    /**
     * 榜单
     * @param page
     */
    @GET("toplist")
    Observable<BaseYuBaResponse<FindFeedInfo>> getTopicList(@Header("client") String header, @Query("page") int page);
}
