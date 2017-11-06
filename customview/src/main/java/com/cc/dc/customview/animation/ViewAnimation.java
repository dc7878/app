package com.cc.dc.customview.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by dc on 2017/11/6.
 */

public class ViewAnimation {

    public static void rotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue,
                                       int pivotYType, float pivotYValue, int duration, View target) {
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        target.startAnimation(animation);
    }

    public static void translateAnimation(float fromXValue, float toXValue, float fromYValue, float toYValue, View target) {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromXValue,
                Animation.RELATIVE_TO_SELF, toXValue,
                Animation.RELATIVE_TO_SELF, fromYValue,
                Animation.RELATIVE_TO_SELF, toYValue);
        animation.setDuration(500);
        animation.setFillAfter(true);
        target.startAnimation(animation);
    }
}
