package com.cc.dc.ui.live.presenter;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.ui.live.contract.LiveAllContract;
import com.cc.dc.ui.live.model.LiveAllModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2017/9/28.
 */
public class LiveAllPresenter extends LiveAllContract.Presenter {

    public LiveAllPresenter() {
        model = new LiveAllModel();
    }

    @Override
    public void loadLiveAllList(int offset, int limit, final boolean isRefresh) {
        model.getLiveAllList(new HttpCallBack<List<LiveBean>>() {
            @Override
            public void onStart(Disposable disposable) {

            }

            @Override
            public void onResult(List<LiveBean> result) {
                view.showLiveAllList(result, isRefresh);
            }

            @Override
            public void onError() {

            }
        }, offset, limit, isRefresh);
    }

}
