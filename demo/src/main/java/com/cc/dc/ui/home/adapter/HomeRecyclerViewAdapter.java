package com.cc.dc.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cc.dc.bean.InfoBean;
import com.cc.dc.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/9/19.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder> {

    private List<InfoBean> data;

    private LayoutInflater inflater;

    public HomeRecyclerViewAdapter(Context context, List<InfoBean> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvDesc.setText(data.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_desc)
        TextView tvDesc;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
