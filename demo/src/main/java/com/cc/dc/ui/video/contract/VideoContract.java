package com.cc.dc.ui.video.contract;

import com.cc.dc.bean.VideoHotBean;
import com.cc.dc.common.listener.HttpCallBack;
import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.presenter.BaseView;

import java.util.List;

/**
 * Created by dc on 2017/9/26.
 */
public interface VideoContract {

    interface Model extends BaseModel {
        void getHotVideoList(HttpCallBack<List<VideoHotBean>> callBack, int clickNum);
    }

    interface View extends BaseView {
        void showHotVideoList(List<VideoHotBean> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        abstract void loadHotVideoList(int clickNum);
    }
}
