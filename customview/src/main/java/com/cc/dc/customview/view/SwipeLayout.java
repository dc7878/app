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

//        delete.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("SwipeLayout", "SwipeLayout>>>onClick");
//            }
//        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        deleteW = delete.getMeasuredWidth();
        Log.e("SwipeLayout", "SwipeLayout>>>" + deleteW);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float currentX = ev.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float currentX = event.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("SwipeLayout", "SwipeLayout>>>down");
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = currentX - lastX;
                Log.e("SwipeLayout", "SwipeLayout>>>" + getScrollX() + ">>offsetX>" + offsetX);
                scrollBy((int) -offsetX, 0);
                break;
            case MotionEvent.ACTION_UP:
                Log.e("SwipeLayout", "SwipeLayout>>>" + getScrollX() + ">>deleteW>" + deleteW);
                if (getScrollX() >= deleteW) {
                    scrollTo((int)deleteW,0);
                } else if(getScrollX() < 0){
                    scrollTo(0, 0);
                }
                break;
        }
        lastX = currentX;
        return true;
    }
}
