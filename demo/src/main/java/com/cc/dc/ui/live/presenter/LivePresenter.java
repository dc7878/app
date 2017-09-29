package com.cc.dc.ui.live.presenter;

import com.cc.dc.bean.LiveColumnBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.ui.live.contract.LiveContract;
import com.cc.dc.ui.live.model.LiveModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2017/9/26.
 */
public class LivePresenter extends LiveContract.Presenter {

    public LivePresenter() {
        model = new LiveModel();
    }

    @Override
    public void loadLiveColumnList() {
        model.getLiveColumnList(new HttpCallBack<List<LiveColumnBean>>() {
            @Override
            public void onStart(Disposable disposable) {

            }

            @Override
            public void onResult(List<LiveColumnBean> result) {
                view.showLiveColumnList(result);
            }

            @Override
            public void onError() {

            }
        });
    }
}
