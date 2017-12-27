package com.cc.dc.customview.recyclerviewadapter;

import android.support.annotation.NonNull;

/**
 * Created by dc on 2017/12/27.
 */

public interface AdapterDelegate<T> {

    boolean isForViewType(@NonNull T items, int position);

    int getItemViewLayoutId();

    void convert(ViewHolder holder, T t, int position);
}
