package com.cc.dc.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cc.dc.bean.LiveBean;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by dc on 2017/9/19.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int space = 0;
    private int pos;
    private List<LiveBean> data;

    private int spanCount;

    private HashMap<Integer, Integer>[] maps;
    private int[] heights;

    public GridItemDecoration(int spanCount, int space, List<LiveBean> data) {
        this.spanCount = spanCount;
        this.space = space;
        this.data = data;

        maps = new HashMap[spanCount];
        for (int i = 0; i < spanCount; i++) {
            maps[i] = new HashMap<>();
        }
        heights = new int[spanCount];
    }

    /**
     * 数据刷新了
     */
    public void notifyDataSetChanged() {
        if (data.isEmpty()) {
            return;
        }
        initHashMap();
    }

    private void initHashMap() {
        for (int i = 0; i < data.size(); i ++) {
            int height = data.get(i).getHeight();
            for (int j = 0; j < spanCount; j ++) {
                if (getMaxHeight() == heights[j]) {
                    heights[j] += height + space;
                    maps[j].put(i, height);
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
        outRect.top = space;

        //该View在整个RecyclerView中位置。
        pos = parent.getChildAdapterPosition(view);

        if (maps[spanCount - 1].containsKey(pos)) {
            // 右边
            Log.e("Info", "右边>>>" + pos);
            outRect.left = space / 2;
            outRect.right = space;
        } else if (maps[0].containsKey(pos)){
            // 左边
            Log.e("Info", "左边>>>" + pos);
            outRect.left = space;
            outRect.right = space / 2;
        } else {
            // 中间位置
            Log.e("Info", "中间>>" + pos);
            outRect.left = space / 2;
            outRect.right = space / 2;
        }

//        //两列的左边一列
//        if (pos % 2 == 0) {
//            outRect.left = space;
//            outRect.right = space / 2;
//            if (pos == data.size() - 2 || pos == data.size() - 1) {
//                // 最后一行的
//                outRect.bottom = space;
//            }
//        }
//
//        //两列的右边一列
//        if (pos % 2 == 1) {
//            outRect.left = space / 2;
//            outRect.right = space;
//            if (pos == data.size() - 1) {
//                // 最后一行的
//                outRect.bottom = space;
//            }
//        }
    }
}
