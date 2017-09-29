package com.cc.dc.ui.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cc.dc.bean.LiveGameCateBean;
import com.cc.dc.dc.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/9/29.
 */
public class LiveGameCateAdapter extends RecyclerView.Adapter<LiveGameCateAdapter.LiveViewHolder> implements View.OnClickListener {

    private Context context;

    private List<LiveGameCateBean> data;

    private LayoutInflater inflater;

    private OnItemOnClickListener onItemOnClickListener;

    public LiveGameCateAdapter(Context context, List<LiveGameCateBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemOnClickListener(OnItemOnClickListener onItemOnClickListener) {
        this.onItemOnClickListener = onItemOnClickListener;
    }

    @Override
    public LiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_live_game_cate, parent, false);
        view.setOnClickListener(this);
        return new LiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LiveViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getTagName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (null != onItemOnClickListener) {
            onItemOnClickListener.onItemOnClick(v, position);
        }
    }

    public class LiveViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;

        private View itemView;

        public LiveViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemOnClickListener {
        void onItemOnClick(View view,int position);
    }
}
