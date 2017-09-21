package com.cc.dc.ui.main.presenter;

import com.cc.dc.ui.main.contract.HomeCateContract;

/**
 * Created by dc on 2017/9/21.
 */
public class HomePresenter extends HomeCateContract.Presenter {

    @Override
    public void loadData() {
        model.getCateList();
    }
}
