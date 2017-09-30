package com.cc.dc.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.dc.bean.HomeCateListBean;
import com.cc.dc.dc.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/9/30.
 */
public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.HomeRecommendViewHolder> {

    private Context context;

    private List<HomeCateListBean> data;

    private LayoutInflater inflater;

    public HomeRecommendAdapter(Context context, List<HomeCateListBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HomeRecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_recommend, parent, false);
        return new HomeRecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeRecommendViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getGameName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HomeRecommendViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_name)
        TextView tvName;

        public HomeRecommendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
