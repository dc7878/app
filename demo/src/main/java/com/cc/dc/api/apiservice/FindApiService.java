package com.cc.dc.api.apiservice;

import com.cc.dc.bean.find.FindDigistInfo;
import com.cc.dc.bean.find.FindTopicInfo;
import com.cc.dc.bean.find.TopicMessageBean;
import com.cc.dc.common.http.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
    Observable<BaseResponse<List<TopicMessageBean>>> getTopicMessage();

    /**
     * 精选
     * @param page
     */
    @GET("digest")
    Observable<BaseResponse<FindDigistInfo>> getDigestList(@Query("page") int page);

    /**
     * 榜单
     * @param page
     */
    @GET("toplist")
    Observable<BaseResponse<FindTopicInfo>> getTopicList(@Query("page") int page);
}
