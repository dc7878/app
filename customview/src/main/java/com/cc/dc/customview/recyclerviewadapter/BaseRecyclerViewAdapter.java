package com.cc.dc.customview.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dc on 2017/12/27.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    protected List<T> datas;

    private AdapterDelegatesManager delegatesManager;

    public BaseRecyclerViewAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
        delegatesManager = new AdapterDelegatesManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate delegate = delegatesManager.getItemViewDelegate(viewType);
        int layoutId = delegate.getItemViewLayoutId();
        ViewHolder holder = ViewHolder.createViewHolder(context, parent, layoutId);
//        onViewHolderCreated(holder, holder.getItemView());
        return holder;
    }

    public void convert(ViewHolder holder, T t) {
        delegatesManager.convert(holder, t, holder.getAdapterPosition());
    }

    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            delegatesManager.getItemViewType(datas.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    protected boolean useItemViewDelegateManager() {
        return delegatesManager.getAdapterDelagateCount() > 0;
    }

    public BaseRecyclerViewAdapter addAdapterDelagate(AdapterDelegate<T> delegate) {
        delegatesManager.addAdapterDelegate(delegate);
        return this;
    }

    public BaseRecyclerViewAdapter addAdapterDelagate(int viewType, AdapterDelegate<T> delegate) {
        delegatesManager.addAdapterDelegate(viewType, delegate);
        return this;
    }
}
