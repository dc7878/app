package com.cc.dc.api.apiservice;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.bean.LiveColumnBean;
import com.cc.dc.common.http.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dc on 2017/9/21.
 */
public interface LiveApiService {

    @GET("v1/getColumnList")
    Observable<BaseResponse<List<LiveColumnBean>>> getColumnList();

    /**
     * 获取直播中的全部item下的直播列表
     * @param offset
     * @param limit
     * @return
     */
    @GET("Live/Roomlist/getAllCateRoomList")
    Observable<BaseResponse<List<LiveBean>>> getLiveAllList(@Query("offset") int offset, @Query("limit") int limit);

    @GET("Live/Roomlist/getCate2RoomList")
    Observable<BaseResponse<List<LiveBean>>> getCate2RoomList(@Query("cate2_id") String cateId, @Query("offset") int offset, @Query("limit") int limit);
}
