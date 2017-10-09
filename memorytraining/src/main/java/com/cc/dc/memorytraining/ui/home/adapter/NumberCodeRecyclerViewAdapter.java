package com.cc.dc.memorytraining.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.dc.memorytraining.R;
import com.cc.dc.memorytraining.bean.NumberCodeBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/10/9.
 */
public class NumberCodeRecyclerViewAdapter extends RecyclerView.Adapter<NumberCodeRecyclerViewAdapter.NumberCodeViewHolder> {

    private List<NumberCodeBean> data;
    private LayoutInflater inflater;

    public NumberCodeRecyclerViewAdapter(Context context, List<NumberCodeBean> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NumberCodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_number, parent, false);
        NumberCodeViewHolder holder = new NumberCodeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NumberCodeViewHolder holder, int position) {
        holder.tvDesc.setText(data.get(position).getDesc());
        holder.ivImage.setImageResource(data.get(position).getResId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NumberCodeViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_image)
        ImageView ivImage;
        @Bind(R.id.tv_desc)
        TextView tvDesc;

        public NumberCodeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
