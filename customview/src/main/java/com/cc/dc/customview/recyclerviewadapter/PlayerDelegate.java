package com.cc.dc.customview.recyclerviewadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.cc.dc.customview.R;
import com.cc.dc.customview.bean.TestPlayerBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/12/27.
 */

public class PlayerDelegate implements AdapterDelegate<TestPlayerBean> {

    @Override
    public boolean isForViewType(@NonNull TestPlayerBean items, int position) {
        return true;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_list_test_player;
    }

    @Override
    public void convert(ViewHolder holder, TestPlayerBean testPlayerBean, int position) {
        PlayerAdapter.PlayerViewHolder playerViewHolder = (PlayerAdapter.PlayerViewHolder) holder;
        playerViewHolder.tvTitle.setText(testPlayerBean.getTitle());
    }
}
