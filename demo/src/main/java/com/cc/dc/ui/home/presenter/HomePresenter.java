package com.cc.dc.ui.home.presenter;

import com.cc.dc.bean.HomeCateBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.ui.home.contract.HomeCateContract;
import com.cc.dc.ui.home.model.HomeModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2017/9/21.
 */
public class HomePresenter extends HomeCateContract.Presenter {

    public HomePresenter() {
        model = new HomeModel();
    }

    @Override
    public void loadData() {
        model.getCateList(new HttpCallBack<List<HomeCateBean>>() {
            @Override
            public void onStart(Disposable disposable) {

            }

            @Override
            public void onResult(List<HomeCateBean> result) {
                view.showHomeCateList(result);
            }

            @Override
            public void onError() {

            }
        });
    }
}
