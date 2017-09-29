package com.cc.dc.ui.live.contract;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

import java.util.List;

/**
 * Created by dc on 2017/9/28.
 */
public interface LiveAllContract {

    interface Model extends BaseModel {
        void getLiveAllList(HttpCallBack<List<LiveBean>> callBack, int offset, int limit, boolean
                isRefresh);
    }

    interface View extends BaseView {
        void showLiveAllList(List<LiveBean> cateList, boolean isRefresh);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loadLiveAllList(int offset, int limit, boolean isRefresh);
    }
}
