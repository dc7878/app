package com.cc.dc.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cc.dc.common.presenter.BaseModel;
import com.cc.dc.common.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by dc on 2017/9/18.
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity  {

    private T presenter;

    private E model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() == 0) {
            throw new IllegalArgumentException("没有设置Layout");
        }
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initPresenter();
//        EventBus.getDefault().register(this);
    }

    public abstract int getLayoutId();

    public abstract void initPresenter();

    public abstract void initView();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}

