package com.cc.dc.ui.find.contract;

import com.cc.dc.bean.find.FindFeedBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

import java.util.List;

/**
 * Created by dc on 2017/10/23.
 */
public interface FindFeedContract {

    interface Model extends BaseModel {
        void getFindFeedList(HttpCallBack<List<FindFeedBean>> callBack, int page);
    }

    interface View extends BaseView {
        void showFindFeedList(List<FindFeedBean> list);
    }

    abstract class Presenter extends BasePresenter<FindFeedContract.View, FindFeedContract.Model> {
        public abstract void loadFindFeedList(int page);
    }
}
