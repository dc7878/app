package com.cc.dc.customview.recyclerviewadapter;

import android.support.v4.util.SparseArrayCompat;

/**
 * Created by dc on 2017/12/27.
 */

public class AdapterDelegatesManager<T> {

    private SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat<>();


    public AdapterDelegatesManager<T> addAdapterDelegate(AdapterDelegate<T> delegate) {
        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType, delegate);
        }
        return this;
    }

    public AdapterDelegatesManager<T> addAdapterDelegate(int viewType, AdapterDelegate<T> delegate) {
        if (delegates.get(viewType) != null) {
            throw new IllegalArgumentException("current viewType adapterDelegate is already exist");
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public int getItemViewType(T item, int position) {
        int count = delegates.size();
        for (int i = 0; i < count; i++) {
            AdapterDelegate<T> delegate = delegates.get(i);
            if (delegate.isForViewType(item, position)) {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException("No adapterDelegate find");
    }

    public void convert(ViewHolder holder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            AdapterDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType( item, position))
            {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException("No adapterDelegate find");
    }

    public AdapterDelegate getItemViewDelegate(int viewType) {
        return delegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType) {
        return delegates.get(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(AdapterDelegate<T> delegate) {
        return delegates.indexOfValue(delegate);
    }

    public int getAdapterDelagateCount() {
        return delegates.size();
    }
}
