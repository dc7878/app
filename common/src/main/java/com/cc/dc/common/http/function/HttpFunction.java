package com.cc.dc.common.http.function;

import com.cc.dc.common.http.ApiException;
import com.cc.dc.common.http.BaseResponse;

import io.reactivex.functions.Function;

/**
 * Created by dc on 2017/9/21.
 * 标准的返回数据需要map转换一下
 */
public class HttpFunction <T> implements Function<BaseResponse<T>,T> {

    @Override
    public T apply(BaseResponse<T> baseResponse) throws Exception {
        if (!baseResponse.isSuccess()) {
            throw new ApiException(baseResponse.getError(), "dd");
        }
        return baseResponse.getData();
    }
}
