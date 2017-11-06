package com.cc.dc.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dc on 2017/11/6.
 */

public class CustomLayout extends ViewGroup {

    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentDesireWidth = 0;
        int parentDesireHeight = 0;
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                CustomLayoutParams params = (CustomLayoutParams) child.getLayoutParams();
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

                parentDesireWidth += child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                parentDesireHeight += child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
           }
           parentDesireWidth += getPaddingLeft() + getPaddingRight();
           parentDesireHeight += getPaddingTop() + getPaddingBottom();

           parentDesireWidth = Math.max(parentDesireWidth, getSuggestedMinimumWidth());
           parentDesireHeight = Math.max(parentDesireHeight, getSuggestedMinimumHeight());
        }

        setMeasuredDimension(resolveSize(parentDesireWidth, widthMeasureSpec), resolveSize(parentDesireHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 0) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int height = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                CustomLayoutParams params = (CustomLayoutParams) child.getLayoutParams();
                child.layout(
                        paddingLeft + params.leftMargin,
                        height + paddingTop + params.topMargin,
                        child.getMeasuredWidth() + paddingLeft + params.leftMargin,
                        child.getMeasuredHeight() + height + paddingTop + params.topMargin);

                height += child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            }
        }
    }

    @Override
    protected CustomLayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected CustomLayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    class CustomLayoutParams extends MarginLayoutParams {
        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
