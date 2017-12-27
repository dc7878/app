package com.cc.dc.customview.recyclerviewadapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cc.dc.customview.R;
import com.cc.dc.customview.bean.TestPlayerBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/12/27.
 */

public class PlayerAdapter extends BaseRecyclerViewAdapter<TestPlayerBean>{

    public PlayerAdapter(Context context, List<TestPlayerBean> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.tvTitle.setText(datas.get(position).getTitle());
    }

    public static class PlayerViewHolder extends ViewHolder {

        @Bind(R.id.tv_title)
        TextView tvTitle;

        public PlayerViewHolder(Context context, View itemView) {
            super(context, itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
