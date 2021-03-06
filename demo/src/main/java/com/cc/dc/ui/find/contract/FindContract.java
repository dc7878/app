package com.cc.dc.ui.find.contract;

import com.cc.dc.bean.find.FindFeedBean;
import com.cc.dc.bean.find.TopicMessageBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

import java.util.List;

/**
 * Created by dc on 2017/9/26.
 */
public interface FindContract {

    interface Model extends BaseModel {

        void getTopicMessageList(HttpCallBack<List<TopicMessageBean>> callBack);

        void getDigestList(HttpCallBack<List<FindFeedBean>> callBack, int page);

        void getTopicList(HttpCallBack<List<FindFeedBean>> callBack, int page);
    }

    interface View extends BaseView {

        void showTopicMessageList(List<TopicMessageBean> list);

        void showDigestList(List<FindFeedBean> list);

        void showTopicList(List<FindFeedBean> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void loadTopicMessageList();

        public abstract void loadDigestList(int page);

        public abstract void loadTopicList(int page);
    }
}
