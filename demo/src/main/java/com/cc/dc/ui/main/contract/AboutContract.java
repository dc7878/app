package com.cc.dc.ui.main.contract;

import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

/**
 * Created by dc on 2017/9/18.
 */

public interface AboutContract {

    interface Model extends BaseModel {
        int getInfo();
    }

    interface View extends BaseView {
        void click();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loadRequest();
    }
}
