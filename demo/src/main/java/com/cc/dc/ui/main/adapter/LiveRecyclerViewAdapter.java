package com.cc.dc.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cc.dc.bean.InfoBean;
import com.cc.dc.bean.LiveBean;
import com.cc.dc.dc.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/9/19.
 */
public class LiveRecyclerViewAdapter extends RecyclerView.Adapter<LiveRecyclerViewAdapter.LiveViewHolder> {

    private List<LiveBean> data;

    private LayoutInflater inflater;

    public LiveRecyclerViewAdapter(Context context, List<LiveBean> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public LiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_live, parent, false);
        return new LiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LiveViewHolder holder, int position) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.tvName.getLayoutParams();
        params.height = data.get(position).getHeight();
        holder.tvName.setLayoutParams(params);
        holder.tvName.setText(data.get(position).getName());
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
