package com.cc.dc.ui.live.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.apiservice.LiveApiService;
import com.cc.dc.bean.LiveBean;
import com.cc.dc.bean.LiveGameCateBean;
import com.cc.dc.common.http.function.HttpFunction;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.json.LiveGameCateInfo;
import com.cc.dc.ui.live.contract.LiveCateContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dc on 2017/9/28.
 */
public class LiveCateModel implements LiveCateContract.Model {

    @Override
    public void getLiveGameCateList(final HttpCallBack<List<LiveGameCateBean>> callBack, final String
            shortName) {
        ApiHelper.getInstanceApiV2()
                .create(LiveApiService.class)
                .getGameCateList(shortName)
                .map(new HttpFunction<LiveGameCateInfo>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveGameCateInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                        LUtil.e("LiveCateFragmentInfo", "onSubscribe>>>" + shortName);
                    }

                    @Override
                    public void onNext(LiveGameCateInfo value) {
                        callBack.onResult(value.getList());
                        LUtil.e("LiveCateFragmentInfo", "onNext>>>" + shortName);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError();
                        LUtil.e("LiveCateFragmentInfo", "onError>>>" + shortName + ">>>" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LUtil.e("LiveCateFragmentInfo", "onComplete>>>" + shortName);
                    }
                });
    }

    @Override
    public void getLiveCateList(final HttpCallBack<List<LiveBean>> callBack, int level, String cateId, int offset, int limit, boolean
            isRefresh) {
        switch (level) {
            case 1:
                getCate1RoomList(callBack, cateId, offset, limit);
                break;
            case 2:
                getCate2RoomList(callBack, cateId, offset, limit);
                break;
        }
    }

    private void getCate1RoomList(final HttpCallBack<List<LiveBean>> callBack, String cateId, int offset, int limit) {
        ApiHelper.getInstanceApiV2()
                .create(LiveApiService.class)
                .getCate1RoomList(cateId, offset, limit)
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

    private void getCate2RoomList(final HttpCallBack<List<LiveBean>> callBack, String cateId, int offset, int limit) {
        ApiHelper.getInstanceApiV2()
                .create(LiveApiService.class)
                .getCate2RoomList(cateId, offset, limit)
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
}
