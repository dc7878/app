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
import com.cc.dc.customview.utils.ScreenUtil;

/**
 * Created by dc on 2017/12/15.
 * 各种举例说明
 * http://daemon369.github.io/android/2014/08/17/android-onInterceptTouchEvent-onTouchEvent
 */

public class RefreshLayout extends FrameLayout {

    private View header;

    private View footer;

    private View content;

    private int headerHeight;

    private int footerHeight;

    private float lastY;

    private FrameLayout.LayoutParams paramsHeader;

    private FrameLayout.LayoutParams paramsFooter;

    private FrameLayout.LayoutParams paramsContent;

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
        footerHeight = DensityUtil.dip2px(getContext(), 150);

        header = LayoutInflater.from(getContext()).inflate(R.layout.layout_header, null);
        content = LayoutInflater.from(getContext()).inflate(R.layout.layout_content, null);
        footer = LayoutInflater.from(getContext()).inflate(R.layout.layout_footer, null);

        paramsHeader = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, headerHeight);
        header.setLayoutParams(paramsHeader);

        paramsContent = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenHeight(getContext()));
        paramsContent.topMargin = headerHeight;
        content.setLayoutParams(paramsContent);

        paramsFooter = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, footerHeight);
        footer.setLayoutParams(paramsFooter);

        addView(header, 0);
        addView(content, 1);
        addView(footer, 2);

        scrollBy(0, headerHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paramsFooter.topMargin = headerHeight + content.getMeasuredHeight();
        requestLayout();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float  y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("RefreshLayout", "RefreshLayout onTouchEvent ACTION_DOWN--" + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetY = y - lastY;
                Log.e("RefreshLayout", "RefreshLayout onTouchEvent ACTION_MOVE--" + offsetY);
                if (Math.abs(offsetY) > 2) {
                    updateMargin(-offsetY);
                    lastY = y;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e("RefreshLayout", "RefreshLayout onTouchEvent ACTION_UP--" + getScrollY() + "---" + headerHeight);
                int scrollY = getScrollY() - headerHeight;
                if (scrollY > footerHeight / 2) {
                    // 向上滑动超过1/2
                    scrollTo(0, footerHeight + headerHeight);
                } else if (scrollY < -headerHeight / 2) {
                    // 向下滑动超过1/2
                    scrollTo(0, 0);
                }else {
                    scrollTo(0, 0);
                }
                break;
        }
        lastY = y;
        return true;
    }

    private void updateMargin(float offsetMargin) {
        scrollBy(0, (int) offsetMargin);
    }
}
