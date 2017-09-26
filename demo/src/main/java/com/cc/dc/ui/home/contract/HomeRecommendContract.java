package com.cc.dc.ui.home.contract;

import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

import java.util.List;

/**
 * Created by dc on 2017/9/22.
 */
public interface HomeRecommendContract {

    interface Model extends BaseModel {
        void getHomeRecommendSliderList(HttpCallBack<List<HomeRecommendSliderBean>> callBack);
    }

    interface View extends BaseView {
        void showSliderList(List<HomeRecommendSliderBean> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loadSliderList();
    }
}
