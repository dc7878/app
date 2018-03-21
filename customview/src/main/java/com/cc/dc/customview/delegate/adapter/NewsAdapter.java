package com.cc.dc.customview.delegate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cc.dc.customview.delegate.bean.NewsEntity;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.List;

/**
 * Created by dc on 2018/2/28.
 * 新闻的Adapter
 */

public class NewsAdapter extends RecyclerView.Adapter {

    private Context context;
    private AdapterDelegatesManager<List<NewsEntity>> delegatesManager;

    private List<NewsEntity> list;


    public NewsAdapter(Context context, List<NewsEntity> list) {
        this.list = list;
        this.context = context;
        delegatesManager = new AdapterDelegatesManager<>();

        delegatesManager.addDelegate(new NewsNormalAdapter(context));
        delegatesManager.addDelegate(new NewsVideoAdapter(context));
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(list, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(list, position, holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
