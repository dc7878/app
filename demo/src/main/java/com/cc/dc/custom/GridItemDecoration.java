package com.cc.dc.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.bean.MultipleColumnBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dc on 2017/9/19.
 */
public class GridItemDecoration<T extends MultipleColumnBean> extends RecyclerView.ItemDecoration {

    private int pos;
    private List<T> data;
    private int spanCount;
    private int leftSpace;
    private int topSpace;
    private int rightSpace;
    private int bottomSpace;
    private int middleSpace;

    private HashMap<Integer, Integer>[] maps;
    private int[] heights;
    // 存储最后四个位置上的position
    private int[] lastPosition;

    private GridItemDecoration(int spanCount, List<T> data, int leftSpace, int topSpace, int rightSpace, int bottomSpace, int middleSpace) {
        this.spanCount = spanCount;
        this.data = data;
        this.leftSpace = leftSpace;
        this.topSpace = topSpace;
        this.rightSpace = rightSpace;
        this.bottomSpace = bottomSpace;
        this.middleSpace = middleSpace;

        maps = new HashMap[spanCount];
        for (int i = 0; i < spanCount; i++) {
            maps[i] = new HashMap<>();
        }
        heights = new int[spanCount];
        lastPosition = new int[spanCount];
    }

    /**
     * 数据刷新了
     */
    public void notifyDataSetChanged() {
        if (data.isEmpty()) {
            return;
        }
        reset();
        initHashMap();
    }

    private void reset() {
        for (int i = 0; i < spanCount; i++) {
            maps[i].clear();
            lastPosition[i] = 0;
            heights[i] = 0;
        }
    }

    private void initHashMap() {
        for (int i = 0; i < data.size(); i ++) {
            int height = data.get(i).getHeight();
            for (int j = 0; j < spanCount; j ++) {
                if (getMaxHeight() == heights[j]) {
                    heights[j] += height + topSpace;
                    maps[j].put(i, height);
                    lastPosition[j] = i;
                    break;
                }
            }
        }
    }

    private int getMaxHeight() {
        int max = heights[0];
        for (int i = 0; i < heights.length; i ++) {
            max = Math.min(max, heights[i]);
        }
        return max;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        //该View在整个RecyclerView中位置。
        pos = parent.getChildAdapterPosition(view);
        // 设置顶部的距离
        outRect.top = topSpace;
        if (maps[spanCount - 1].containsKey(pos)) {
            // 右边
            outRect.left = middleSpace / 2;
            outRect.right = rightSpace;
        } else if (maps[0].containsKey(pos)){
            // 左边
            outRect.left = leftSpace;
            outRect.right = middleSpace / 2;
        } else {
            // 中间位置
            outRect.left = middleSpace / 2;
            outRect.right = middleSpace / 2;
        }
        // 设置底部的距离
        for (int i = 0; i < lastPosition.length; i++) {
            if (pos == lastPosition[i]) {
                outRect.bottom = bottomSpace;
            }
        }
    }

    public static final class Builder<T> {
        // 最左侧的Item距离右边的距离
        private int left;
        // 最右测Item距离左边的距离
        private int right;
        // 每两个Item中间的间距
        private int middle;
        // 每个Item距离顶部的高度
        private int top;
        // 最后一行距离底部的高度
        private int bottom;
        // 设置上下左右的间距都是一样的，
        // 如果该值不为0则，left、right、top、bottom、middle
        // 均为该值
        private int eachEqual = -1;
        // 列数
        private int spanCount;
        // 数据集
        private List<T> data;

        public Builder left(int left) {
            this.left = left;
            return this;
        }

        public Builder right(int right) {
            this.right = right;
            return this;
        }

        public Builder middle(int middle) {
            this.middle = middle;
            return this;
        }

        public Builder top(int top) {
            this.top = top;
            return this;
        }

        public Builder bottom(int bottom) {
            this.bottom = bottom;
            return this;
        }

        public Builder eachEqual(int eachEqual) {
            this.eachEqual = eachEqual;
            return this;
        }

        public Builder spanCount(int spanCount) {
            this.spanCount = spanCount;
            return this;
        }

        public Builder data(List<T> data) {
            this.data = data;
            return this;
        }

        public GridItemDecoration build() {
            if (eachEqual != -1) {
                left = eachEqual;
                right = eachEqual;
                top = eachEqual;
                bottom = eachEqual;
                middle = eachEqual;
            }
            return new GridItemDecoration(spanCount, data, left, top, right, bottom, middle);
        }
    }
}
