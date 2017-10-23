package com.cc.dc.ui.find.presenter;

import com.cc.dc.bean.find.FindFeedBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.ui.find.contract.FindFeedContract;
import com.cc.dc.ui.find.model.FindFeedModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2017/10/23.
 */

public class FindFeedPresenter extends FindFeedContract.Presenter {

    public FindFeedPresenter() {
        model = new FindFeedModel();
    }

    @Override
    public void loadFindFeedList(int page) {
        model.getFindFeedList(new HttpCallBack<List<FindFeedBean>>() {
            @Override
            public void onStart(Disposable disposable) {
            }

            @Override
            public void onResult(List<FindFeedBean> result) {
                view.showFindFeedList(result);
            }

            @Override
            public void onError() {

            }
        }, page);
    }
}
