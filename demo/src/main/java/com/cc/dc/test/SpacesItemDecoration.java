package com.cc.dc.test;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cc.dc.common.utils.LUtil;

/**
 * Created by dc on 2017/11/2.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0

        if (parent.getChildLayoutPosition(view) % 6==0) {
            outRect.left = 0;
        }

        LUtil.e("SpacesItemDecoration", "SpacesItemDecoration>>>>" + parent.getChildLayoutPosition(view));
    }
}
