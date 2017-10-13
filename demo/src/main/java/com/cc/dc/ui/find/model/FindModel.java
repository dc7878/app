package com.cc.dc.ui.find.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.apiservice.FindApiService;
import com.cc.dc.bean.find.FindDigistInfo;
import com.cc.dc.bean.find.FindDigestBean;
import com.cc.dc.bean.find.FindTopicBean;
import com.cc.dc.bean.find.FindTopicInfo;
import com.cc.dc.bean.find.TopicMessageBean;
import com.cc.dc.common.http.function.HttpFunction;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.ui.find.contract.FindContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dc on 2017/10/13.
 */
public class FindModel implements FindContract.Model {

    private final String TAG = FindModel.class.getSimpleName();

    @Override
    public void getTopicMessageList(final HttpCallBack<List<TopicMessageBean>> callBack) {
        ApiHelper.getInstance()
                .create(FindApiService.class)
                .getTopicMessage()
                .map(new HttpFunction<List<TopicMessageBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TopicMessageBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(List<TopicMessageBean> value) {
                        callBack.onResult(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError();
                    }

                    @Override
                    public void onComplete() {
                        LUtil.e(TAG, "onComplete");
                    }
                });
    }

    @Override
    public void getDigestList(final HttpCallBack<List<FindDigestBean>> callBack, int page) {
        ApiHelper.getInstance()
                .create(FindApiService.class)
                .getDigestList(page)
                .map(new HttpFunction<FindDigistInfo>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindDigistInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(FindDigistInfo value) {
                        callBack.onResult(value.getList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError();
                    }

                    @Override
                    public void onComplete() {
                        LUtil.e(TAG, "onComplete");
                    }
                });
    }

    @Override
    public void getTopicList(final HttpCallBack<List<FindTopicBean>> callBack, int page) {
        ApiHelper.getInstance()
                .create(FindApiService.class)
                .getTopicList(page)
                .map(new HttpFunction<FindTopicInfo>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindTopicInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(FindTopicInfo value) {
                        callBack.onResult(value.getList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError();
                    }

                    @Override
                    public void onComplete() {
                        LUtil.e(TAG, "onComplete");
                    }
                });
    }
}
