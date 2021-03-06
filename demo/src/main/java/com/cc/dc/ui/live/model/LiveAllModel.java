package com.cc.dc.ui.live.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.apiservice.LiveApiService;
import com.cc.dc.bean.LiveBean;
import com.cc.dc.bean.live.LiveUrlBean;
import com.cc.dc.common.http.function.HttpFunction;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.ui.live.contract.LiveAllContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dc on 2017/9/28.
 */
public class LiveAllModel implements LiveAllContract.Model {

    @Override
    public void getLiveAllList(final HttpCallBack<List<LiveBean>> callBack, int offset, int limit, boolean
            isRefresh) {
        ApiHelper.getInstanceApiV2()
                .create(LiveApiService.class)
                .getLiveAllList(offset, limit)
                .map(new HttpFunction<List<LiveBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LiveBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(List<LiveBean> value) {
                        callBack.onResult(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getLiveUrl(final HttpCallBack<LiveUrlBean> callBack, String roomId) {
        ApiHelper.getInstanceApiLive()
                .create(LiveApiService.class)
                .getLiveUrl(roomId)
                .map(new HttpFunction<LiveUrlBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveUrlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(LiveUrlBean value) {
                        LUtil.e("", "");
                        callBack.onResult(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
