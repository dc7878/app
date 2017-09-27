package com.cc.dc.ui.live.contract;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.bean.LiveColumnBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

import java.util.List;

/**
 * Created by dc on 2017/9/26.
 */
public interface LiveContract {

    interface Model extends BaseModel {
        void getLiveList(HttpCallBack<List<LiveBean>> callBack, int offset, int limit, boolean isRefresh);

        void getLiveColumnList(HttpCallBack<List<LiveColumnBean>> callBack);
    }

    interface View extends BaseView {
        void showLiveList(List<LiveBean> cateList, boolean isRefresh);

        void showLiveColumnList(List<LiveColumnBean> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loadLiveList(int offset, int limit, boolean isRefresh);

        public abstract void loadLiveColumnList();
    }
}
