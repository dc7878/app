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
public class LiveGameCateAdapter extends RecyclerView.Adapter<LiveGameCateAdapter.LiveViewHolder> {

    private Context context;

    private List<LiveGameCateBean> data;

    private LayoutInflater inflater;

    public LiveGameCateAdapter(Context context, List<LiveGameCateBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public LiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_live_game_cate, parent, false);
        return new LiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LiveViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getTagName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LiveViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;

        public LiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
