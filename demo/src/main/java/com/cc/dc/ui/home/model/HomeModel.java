package com.cc.dc.ui.home.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.apiservice.HomeApiService;
import com.cc.dc.bean.HomeCateBean;
import com.cc.dc.common.http.function.HttpFunction;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.ui.home.contract.HomeCateContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dc on 2017/9/21.
 */
public class HomeModel implements HomeCateContract.Model {

    private final String TAG = HomeModel.class.getSimpleName();

    @Override
    public void getCateList(final HttpCallBack<List<HomeCateBean>> callBack) {
        ApiHelper.getInstance()
                .create(HomeApiService.class)
                .getCateList()
                .map(new HttpFunction<List<HomeCateBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HomeCateBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(List<HomeCateBean> value) {
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
}
