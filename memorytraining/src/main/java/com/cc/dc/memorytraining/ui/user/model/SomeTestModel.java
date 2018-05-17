package com.cc.dc.memorytraining.ui.user.model;

import com.cc.dc.common.http.observer.BaseObserver;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.memorytraining.api.ApiHelper;
import com.cc.dc.memorytraining.api.ApiService;
import com.cc.dc.memorytraining.bean.NewsBean;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dc on 2018/5/16.
 */

public class SomeTestModel {

    public static void test(Map<String, String> map) {
        ApiHelper.getInstance()
                .create(ApiService.class)
                .getNewsList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(NewsBean value) {
//                        LUtil.e("SomeTestModel", "SomeTestModel>>>" + value.code + " " + value.data);
                        LUtil.e("SomeTestModel", "SomeTestModel>>>" + value.code);
                        if (value != null) {
                            if (value.data == null) {
                                LUtil.e("SomeTestModel", "SomeTestModel>>> null");
                            } else {
                                LUtil.e("SomeTestModel", "SomeTestModel>>> not null " + value.data.size());
                            }
                        }
//                        for (int i = 0; i < value.data.size(); i++) {
//                            LUtil.e("SomeTestModel", "SomeTestModel>>>" +
//                                    value.data.get(i).title + " " + value.data.get(i).type
//                                    + " " + value.data.get(i).age);
//                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        LUtil.e("SomeTestModel", "onError>>>" + t.getMessage());
                    }
                });
    }
}
