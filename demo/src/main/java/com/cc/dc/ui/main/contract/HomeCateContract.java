package com.cc.dc.ui.main.contract;

import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

/**
 * Created by dc on 2017/9/21.
 */
public interface HomeCateContract {

    interface Model extends BaseModel {
        void getCateList();
    }

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loadData();
    }
}
