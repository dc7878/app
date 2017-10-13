package com.cc.dc.ui.find.presenter;

import com.cc.dc.bean.find.FindDigestBean;
import com.cc.dc.bean.find.FindTopicBean;
import com.cc.dc.bean.find.TopicMessageBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.ui.find.contract.FindContract;
import com.cc.dc.ui.find.model.FindModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2017/10/13.
 */
public class FindPresenter extends FindContract.Presenter {

    public FindPresenter() {
        model = new FindModel();
    }

    @Override
    public void loadTopicMessageList() {
        model.getTopicMessageList(new HttpCallBack<List<TopicMessageBean>>() {
            @Override
            public void onStart(Disposable disposable) {
            }

            @Override
            public void onResult(List<TopicMessageBean> result) {
                view.showTopicMessageList(result);
            }

            @Override
            public void onError() {
            }
        });
    }

    @Override
    public void loadDigestList(int page) {
        model.getDigestList(new HttpCallBack<List<FindDigestBean>>() {
            @Override
            public void onStart(Disposable disposable) {
            }

            @Override
            public void onResult(List<FindDigestBean> result) {
                view.showDigestList(result);
            }

            @Override
            public void onError() {
            }
        }, page);
    }

    @Override
    public void loadTopicList(int page) {
        model.getTopicList(new HttpCallBack<List<FindTopicBean>>() {
            @Override
            public void onStart(Disposable disposable) {
            }

            @Override
            public void onResult(List<FindTopicBean> result) {
                view.showTopicList(result);
            }

            @Override
            public void onError() {
            }
        }, page);
    }
}
