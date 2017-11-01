package com.cc.dc.api;


import com.cc.dc.common.http.ApiException;
import com.cc.dc.common.utils.LUtil;

import io.reactivex.functions.Function;

public class HttpYuBaFunction<T> implements Function<BaseYuBaResponse<T>,T> {

    @Override
    public T apply(BaseYuBaResponse<T> baseResponse) throws Exception {
        LUtil.e("HttpYuBaFunction", "HttpYuBaFunction>>>" + baseResponse.getStatusCode());
        if (!baseResponse.isSuccess()) {
            throw new ApiException(baseResponse.getStatusCode(), baseResponse.getMessage());
        }
        return baseResponse.getData();
    }
}
