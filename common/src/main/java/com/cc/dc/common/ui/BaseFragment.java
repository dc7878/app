package com.cc.dc.common.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.utils.LUtil;

import butterknife.ButterKnife;

/**
 * Created by dc on 2017/9/18.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T presenter;

    private View rootView;
    // 用户是否可见
    private boolean isVisible;
    // 标识fragment视图是否已经初始化完毕
    private boolean isViewPrepared;
    // 标识是否已经触发过懒加载数据
    private boolean hasLoadData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        if (getLayoutId() != 0) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
        }
        initView();
        initPresenter();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LUtil.e("BaseFragment", getClass().getSimpleName() + ">>>onViewCreated>>>");
        isViewPrepared = true;
        lazyLoadDataPrepared();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initPresenter();

    public abstract void lazyLoadData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadDataPrepared();
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isVisible = !hidden;
        if (!hidden) {
            lazyLoadDataPrepared();
        }
    }

    private void lazyLoadDataPrepared() {
        LUtil.e("BaseFragment", getClass().getSimpleName() + ">>>" + isVisible + ">>>" + !hasLoadData + "--" + isViewPrepared);
        if (isVisible && !hasLoadData && isViewPrepared) {
            hasLoadData = true;
            lazyLoadData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
