package com.cc.dc.util.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.dc.R;

/**
 * Created by dc on 2017/12/12.
 */

public class ViewStatusHelper implements IViewStatus {

    private View targetView;

    private View emptyView;

    private View netErrorView;

    private ViewGroup parentView;

    private LayoutInflater inflater;

    private int currentIndex;

    private ViewGroup.LayoutParams params;

    public ViewStatusHelper(Context context, View targetView) {
        this.targetView = targetView;

        inflater = LayoutInflater.from(context);
        init();
    }

    private void init() {

        params = targetView.getLayoutParams();

        if (targetView.getParent() != null) {
            parentView = (ViewGroup) targetView.getParent();
        } else {
            parentView = targetView.getRootView().findViewById(android.R.id.content);
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
        showView(netErrorView);
    }

    @Override
    public void showNormal() {
        showView(targetView);
    }
}
