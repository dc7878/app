package com.cc.dc.ui.main.presenter;

import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.ui.main.contract.HomeRecommendContract;
import com.cc.dc.ui.main.model.HomeRecommendModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2017/9/22.
 */
public class HomeRecommendPresenter extends HomeRecommendContract.Presenter {

    public HomeRecommendPresenter() {
        model = new HomeRecommendModel();
    }

    @Override
    public void loadSliderList() {
        model.getHomeRecommendSliderList(new HttpCallBack<List<HomeRecommendSliderBean>>() {
            @Override
            public void onStart(Disposable disposable) {

            }

            @Override
            public void onResult(List<HomeRecommendSliderBean> result) {
                view.showSliderList(result);
            }

            @Override
            public void onError() {

            }
        });
    }
}
