package com.cc.dc.ui.find.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.HttpYuBaFunction;
import com.cc.dc.api.apiservice.FindApiService;
import com.cc.dc.bean.find.FindFeedBean;
import com.cc.dc.bean.find.FindFeedInfo;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.ui.find.contract.FindFeedContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dc on 2017/10/23.
 */

public class FindFeedModel implements FindFeedContract.Model {

    private final String TAG = FindFeedModel.class.getSimpleName();

    @Override
    public void getFindFeedList(final HttpCallBack<List<FindFeedBean>> callBack, int page) {
        ApiHelper.getInstanceApi3()
                .create(FindApiService.class)
                .getDigestList("android", page)
                .map(new HttpYuBaFunction<FindFeedInfo>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindFeedInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(FindFeedInfo value) {
                        callBack.onResult(value.getList());
                        LUtil.e(TAG, "onNext>>>" + value.getList().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LUtil.e(TAG, "onError");
                        callBack.onError();
                    }

                    @Override
                    public void onComplete() {
                        LUtil.e(TAG, "onComplete");
                    }
                });
    }
}
