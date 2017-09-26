package com.cc.dc.ui.home.contract;

import com.cc.dc.bean.HomeCateBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

import java.util.List;

/**
 * Created by dc on 2017/9/21.
 */
public interface HomeCateContract {

    interface Model extends BaseModel {
        void getCateList(HttpCallBack<List<HomeCateBean>> callBack);
    }

    interface View extends BaseView {
        void showHomeCateList(List<HomeCateBean> cateList);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loadData();
    }
}
