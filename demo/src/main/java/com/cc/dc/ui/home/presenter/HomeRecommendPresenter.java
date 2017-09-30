package com.cc.dc.ui.home.presenter;

import com.cc.dc.bean.HomeCateDataBean;
import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.ui.home.contract.HomeRecommendContract;
import com.cc.dc.ui.home.model.HomeRecommendModel;

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

    @Override
    public void loadBigDataRoomList(String aid, String time, String auth) {
        model.getBigDataRoomList(new HttpCallBack<List<HomeCateDataBean>>() {
            @Override
            public void onStart(Disposable disposable) {
            }

            @Override
            public void onResult(List<HomeCateDataBean> result) {
                LUtil.e("HomeRecommendPresenter", "loadBigDataRoomList>>>" + result.size());
            }

            @Override
            public void onError() {

            }
        }, aid, time, auth);
    }

    @Override
    public void loadHomeCustomList() {
        model.getHomeCustomList(new HttpCallBack<List<HomeCateDataBean>>() {
            @Override
            public void onStart(Disposable disposable) {
            }

            @Override
            public void onResult(List<HomeCateDataBean> result) {
                LUtil.e("HomeRecommendPresenter", "loadHomeCustomList>>>" + result.size());
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void loadHotCateList(String aid, String time, String auth) {
        model.getHotCateList(new HttpCallBack<List<HomeCateDataBean>>() {
            @Override
            public void onStart(Disposable disposable) {
            }

            @Override
            public void onResult(List<HomeCateDataBean> result) {
                LUtil.e("HomeRecommendPresenter", "loadHotCateList>>>" + result.size());
            }

            @Override
            public void onError() {
            }
        }, aid, time, auth);
    }
}
