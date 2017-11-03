package com.cc.dc.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by dc on 2017/11/2.
 */
public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int halfWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width / 3 * 2, widthMode);
        super.onMeasure(widthMeasureSpec, halfWidthMeasureSpec);
    }
}
