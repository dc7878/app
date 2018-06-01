package com.cc.dc.kotlindemo.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.dc.kotlindemo.R;
import com.cc.dc.kotlindemo.listener.LoadingErrorListener;
import com.cc.dc.kotlindemo.listener.ViewStatusListener;


/**
 * Created by dc on 2018/3/6.
 */

public class ViewStatusUtil implements ViewStatusListener, View.OnClickListener {

    private View targetView;
    private View emptyView;
    private View netErrorView;
    private View loadingView;

    private ViewGroup parentView;
    private LayoutInflater inflater;
    private int currentIndex;
    private ViewGroup.LayoutParams params;


    private LoadingErrorListener listener;

    public ViewStatusUtil(Context context, View targetView) {
        this.targetView = targetView;
        inflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        params = targetView.getLayoutParams();
        if (targetView.getParent() != null) {
            parentView = (ViewGroup)targetView.getParent();
        } else {
            parentView = (ViewGroup)targetView.getRootView().findViewById(android.R.id.content);
        }
        int count = parentView.getChildCount();
        for (int i = 0; i < count; i++) {
            if (targetView == parentView.getChildAt(i)) {
                currentIndex = i;
                break;
            }
        }
    }

    private void showView(View view) {
        if (parentView.getChildAt(currentIndex) == view) {
            return;
        }
        parentView.removeViewAt(currentIndex);
        parentView.addView(view, currentIndex, params);
    }

    @Override
    public void showEmpty() {
        if (emptyView != null) {
            showView(emptyView);
            return;
        }
        emptyView = inflater.inflate(R.layout.layout_empty, null);
        showView(emptyView);
    }

    @Override
    public void showNetError() {
        if (netErrorView != null) {
            showView(netErrorView);
            return;
        }
        netErrorView = inflater.inflate(R.layout.layout_net_error, null);
        netErrorView.setOnClickListener(this);
        showView(netErrorView);
    }

    @Override
    public void showLoading() {
        if (loadingView != null) {
            showView(loadingView);
            return;
        }
        loadingView = inflater.inflate(R.layout.layout_loading, null);
        showView(loadingView);
    }

    @Override
    public void showNormal() {
        showView(targetView);
    }

    public void setLoadingErrorListener(LoadingErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        Log.e("ViewStatusUtil", "onClick");
        if (listener != null) {
            listener.loadError();
        }
    }
}
