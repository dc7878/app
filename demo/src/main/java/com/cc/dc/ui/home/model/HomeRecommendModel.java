package com.cc.dc.ui.home.model;

import com.cc.dc.api.ApiHelper;
import com.cc.dc.api.apiservice.HomeApiService;
import com.cc.dc.bean.HomeCateDataBean;
import com.cc.dc.bean.HomeCateInfoBean;
import com.cc.dc.bean.HomeCateListBean;
import com.cc.dc.bean.HomeHotDataBean;
import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.http.function.HttpFunction;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.ui.home.contract.HomeRecommendContract;

import java.util.ArrayList;
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

    @Override
    public void getBigDataRoomList(final HttpCallBack<List<HomeCateDataBean>> callBack, String aid,
                                   String time, String auth) {
        ApiHelper.getInstance()
                .create(HomeApiService.class)
                .getBigDataRoomList(aid, time, auth)
                .map(new HttpFunction<List<HomeCateListBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HomeCateListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(List<HomeCateListBean> value) {
                        List<HomeCateDataBean> list = new ArrayList<>();

                        HomeCateInfoBean bean = new HomeCateInfoBean();
                        List<HomeCateListBean> cateList = new ArrayList<>();

                        for (HomeCateListBean info: value) {
                            HomeCateListBean cateBean = new HomeCateListBean();
                            cateBean.setGameName(info.getGameName());
                            cateBean.setOnline(info.getOnline());
                            cateBean.setRoomId(info.getRoomId());
                            cateBean.setRoomName(info.getRoomName());
                            cateList.add(cateBean);
                        }
                        bean.setCateName("最热");
                        bean.setIconUrl("http://www.learn2sleep.com/icon.jpg");
                        HomeCateDataBean cateDataBean = new HomeCateDataBean();
                        cateDataBean.setCateInfo(bean);
                        cateDataBean.setList(cateList);
                        list.add(cateDataBean);
                        callBack.onResult(list);
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
    public void getHomeCustomList(final HttpCallBack<List<HomeCateDataBean>> callBack) {
        ApiHelper.getInstanceApiV2()
                .create(HomeApiService.class)
                .getHomeCustomList()
                .map(new HttpFunction<List<HomeCateDataBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HomeCateDataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(List<HomeCateDataBean> value) {
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
    public void getHotCateList(final HttpCallBack<List<HomeCateDataBean>> callBack, String aid, String time, String auth) {
        ApiHelper.getInstance()
                .create(HomeApiService.class)
                .getHomeHotList(aid, time, auth)
                .map(new HttpFunction<List<HomeHotDataBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HomeHotDataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart(d);
                    }

                    @Override
                    public void onNext(List<HomeHotDataBean> value) {
                        List<HomeCateDataBean> list = new ArrayList<>();
                        for (HomeHotDataBean bean: value) {
                            HomeCateDataBean cateDataBean = new HomeCateDataBean();
                            HomeCateInfoBean cateInfo = new HomeCateInfoBean();
                            cateInfo.setCateName(bean.getCateName());
                            cateInfo.setIconUrl(bean.getIconUrl());
                            cateDataBean.setList(bean.getRoomList());
                            cateDataBean.setCateInfo(cateInfo);
                            list.add(cateDataBean);
                        }
                        callBack.onResult(list);
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
