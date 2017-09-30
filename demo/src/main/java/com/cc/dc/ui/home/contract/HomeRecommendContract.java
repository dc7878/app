package com.cc.dc.ui.home.contract;

import com.cc.dc.bean.HomeCateDataBean;
import com.cc.dc.bean.HomeCateInfoBean;
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

        void getBigDataRoomList(HttpCallBack<List<HomeCateDataBean>> callBack, String aid, String time, String auth);

        void getHomeCustomList(HttpCallBack<List<HomeCateDataBean>> callBack);

        void getHotCateList(HttpCallBack<List<HomeCateDataBean>> callBack, String aid, String time, String auth);
    }

    interface View extends BaseView {
        void showSliderList(List<HomeRecommendSliderBean> list);

        void showBigDataRoomList(List<HomeCateDataBean> list);

        void showHomeCustomList(List<HomeCateDataBean> list);

        void showHotCateList(List<HomeCateDataBean> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loadSliderList();

        public abstract void loadBigDataRoomList(String aid, String time, String auth);

        public abstract void loadHomeCustomList();

        public abstract void loadHotCateList(String aid, String time, String auth);
    }
}
