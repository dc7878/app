package com.cc.dc.ui.live.presenter;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.ui.live.contract.LiveCateContract;
import com.cc.dc.ui.live.model.LiveCateModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2017/9/28.
 */
public class LiveCatePresenter extends LiveCateContract.Presenter {

    public LiveCatePresenter() {
        model = new LiveCateModel();
    }

    @Override
    public void loadCateLiveList(String careId, int offset, int limit, final boolean isRefresh) {
        model.getLiveCateList(new HttpCallBack<List<LiveBean>>() {
            @Override
            public void onStart(Disposable disposable) {

            }

            @Override
            public void onResult(List<LiveBean> result) {
                view.showLiveCateList(result, isRefresh);
            }

            @Override
            public void onError() {

            }
        }, careId, offset, limit, isRefresh);
    }
}
