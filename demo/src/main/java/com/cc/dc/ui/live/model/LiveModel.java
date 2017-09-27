package com.cc.dc.ui.live.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.apiservice.LiveApiService;
import com.cc.dc.bean.LiveBean;
import com.cc.dc.bean.LiveColumnBean;
import com.cc.dc.common.http.function.HttpFunction;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.ui.live.contract.LiveContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dc on 2017/9/26.
 */
public class LiveModel implements LiveContract.Model {

    @Override
    public void getLiveList(final HttpCallBack<List<LiveBean>> callBack, int offset, int limit, boolean
            isRefresh) {
        ApiHelper.getInstance()
                .create(LiveApiService.class)
                .getLiveList(offset, limit)
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
    public void getLiveColumnList(final HttpCallBack<List<LiveColumnBean>> callBack) {
        ApiHelper.getInstance()
                .create(LiveApiService.class)
                .getColumnList()
                .map(new HttpFunction<List<LiveColumnBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LiveColumnBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(List<LiveColumnBean> value) {
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
