package com.cc.dc.customview.delegate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.cc.dc.customview.R;
import com.cc.dc.customview.delegate.bean.NewsEntity;
import com.cc.dc.customview.delegate.bean.NewsNormalEntity;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;


/**
 * Created by dc on 2018/2/28.
 * 新闻默认的Adapter
 */

public class NewsNormalAdapter extends AbsListItemAdapterDelegate<NewsNormalEntity, NewsEntity, NewsNormalAdapter.NormalViewHolder> {

    private LayoutInflater inflater;

    private Context context;

    public NewsNormalAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    protected boolean isForViewType(@NonNull NewsEntity item, @NonNull List<NewsEntity> items, int position) {
        return item instanceof NewsNormalEntity;
    }

    @NonNull
    @Override
    protected NormalViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_list_test_normal, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsNormalEntity item, @NonNull NormalViewHolder viewHolder, @NonNull List<Object> payloads) {
        // 分割线、分隔条
    }

    static class NormalViewHolder extends RecyclerView.ViewHolder {


        private NormalViewHolder(View itemView) {
            super(itemView);
        }
    }
}
