package com.cc.dc.customview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by dc on 2017/12/19.
 */

public class SwipeLayout extends LinearLayout {

    private float lastX;

    private View content;
    private View delete;

    private float deleteW;

    private float currentX;

    public SwipeLayout(Context context) {
        super(context);
    }

    public SwipeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        content = getChildAt(0);
        delete = getChildAt(1);

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("SwipeLayout", "SwipeLayout>>>onClick");
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        deleteW = delete.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                currentX = ev.getX();
                lastX = currentX;
                break;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                float offsetX = currentX - lastX;
                if ((offsetX < 0 && (getScrollX() - offsetX) <= deleteW) || (offsetX > 0 && (getScrollX() - offsetX) >= 0)) {
                    scrollBy((int) -offsetX, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollX() >= deleteW / 2) {
                    scrollTo((int)deleteW,0);
                } else {
                    scrollTo(0, 0);
                }
                break;
        }
        lastX = currentX;
        return true;
    }
}
