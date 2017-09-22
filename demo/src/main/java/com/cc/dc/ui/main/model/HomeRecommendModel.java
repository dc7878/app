package com.cc.dc.ui.main.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.apiservice.HomeApiService;
import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.http.function.HttpFunction;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.ui.main.contract.HomeRecommendContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dc on 2017/9/22.
 */
public class HomeRecommendModel implements HomeRecommendContract.Model {

    private final String TAG = HomeRecommendModel.class.getSimpleName();

    @Override
    public void getHomeRecommendSliderList(final HttpCallBack<List<HomeRecommendSliderBean>> callBack) {
        ApiHelper.getInstance()
                .create(HomeApiService.class)
                .getHomeSlides()
                .map(new HttpFunction<List<HomeRecommendSliderBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HomeRecommendSliderBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(List<HomeRecommendSliderBean> value) {
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
