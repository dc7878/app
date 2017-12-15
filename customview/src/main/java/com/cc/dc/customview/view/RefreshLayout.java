package com.cc.dc.customview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cc.dc.customview.R;
import com.cc.dc.customview.utils.DensityUtil;

/**
 * Created by dc on 2017/12/15.
 */

public class RefreshLayout extends FrameLayout {

    private View header;

    private View content;

    private int headerHeight;

    private float currentY;

    public RefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        headerHeight = DensityUtil.dip2px(getContext(), 100);

        header = LayoutInflater.from(getContext()).inflate(R.layout.layout_header, null);
        content = LayoutInflater.from(getContext()).inflate(R.layout.layout_content, null);

        FrameLayout.LayoutParams paramsHeader = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, headerHeight);
        header.setLayoutParams(paramsHeader);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.topMargin = headerHeight;
        content.setLayoutParams(params);

        addView(header, 0);
        addView(content, 1);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("RefreshLayout", "RefreshLayout ACTION_DOWN--");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("RefreshLayout", "RefreshLayout ACTION_MOVE--");
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("RefreshLayout", "onTouchEvent ACTION_DOWN--" + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("RefreshLayout", "onTouchEvent ACTION_MOVE--" + event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
