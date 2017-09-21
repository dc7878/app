package com.cc.dc.ui.main.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.apiservice.HomeApiService;
import com.cc.dc.bean.HomeCateBean;
import com.cc.dc.common.http.function.HttpFunction;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.ui.main.contract.HomeCateContract;

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
    public void getCateList() {
        ApiHelper.getInstance()
                .create(HomeApiService.class)
                .getCateList()
                .map(new HttpFunction<List<HomeCateBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HomeCateBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LUtil.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(List<HomeCateBean> value) {
                        for (HomeCateBean bean: value) {
                            LUtil.e(TAG, bean.getTitle());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LUtil.e(TAG, "onError>>>" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LUtil.e(TAG, "onComplete");
                    }
                });

    }
}
