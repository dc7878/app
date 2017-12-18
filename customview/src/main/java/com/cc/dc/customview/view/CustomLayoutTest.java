package com.cc.dc.customview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by dc on 2017/12/18.
 */

public class CustomLayoutTest extends LinearLayout {

    public CustomLayoutTest(Context context) {
        super(context);
        setClickable(true);
    }

    public CustomLayoutTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
    }

    public CustomLayoutTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                Log.e("RefreshLayout", "CustomLayoutTest RefreshLayout ACTION_DOWN--");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e("RefreshLayout", "CustomLayoutTest RefreshLayout ACTION_MOVE--");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e("RefreshLayout", "CustomLayoutTest RefreshLayout ACTION_UP--");
//                break;
//        }
////        boolean flag = super.dispatchTouchEvent(ev);
////        Log.e("RefreshLayout", "CustomLayoutTest RefreshLayout flag--" + flag);
//        return super.dispatchTouchEvent(ev);
//    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("RefreshLayout", "CustomLayoutTest onInterceptTouchEvent ACTION_DOWN--");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("RefreshLayout", "CustomLayoutTest onInterceptTouchEvent ACTION_MOVE--");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("RefreshLayout", "CustomLayoutTest onInterceptTouchEvent ACTION_UP--");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("RefreshLayout", "CustomLayoutTest onTouchEvent ACTION_DOWN--");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("RefreshLayout", "CustomLayoutTest onTouchEvent ACTION_MOVE--");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("RefreshLayout", "CustomLayoutTest onTouchEvent ACTION_UP--");
                break;
        }
        boolean flag = super.onTouchEvent(event);
        Log.e("RefreshLayout", "RefreshLayout>>>flag>>" + flag + ">>>" + super.isClickable());
        return false;
    }
}
