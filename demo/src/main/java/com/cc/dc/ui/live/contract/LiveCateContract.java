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
public interface LiveCateContract {

    interface Model extends BaseModel {
        void getLiveCateList(HttpCallBack<List<LiveBean>> callBack, int level, String careId, int offset, int limit, boolean
                isRefresh);
    }

    interface View extends BaseView {
        void showLiveCateList(List<LiveBean> cateList, boolean isRefresh);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loadCateLiveList(int level, String careId, int offset, int limit, boolean isRefresh);
    }
}
