package com.cc.dc.customview.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dc on 2017/12/27.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private View itemView;

    public ViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
    }

    public static ViewHolder createViewHolder(Context context, View itemView) {
        ViewHolder holder = new ViewHolder(context, itemView);
        return holder;
    }

    public static <H extends ViewHolder>  H createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        ViewHolder holder = new ViewHolder(context, itemView);
        return (H) holder;
    }

    public View getItemView() {
        return itemView;
    }
}
