package com.cc.dc.ui.live.fragment;

import android.content.Intent;
import android.widget.Toast;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.ui.common.activity.PlayVideoActivity;
import com.cc.dc.ui.common.fragment.BaseListFragment;
import com.cc.dc.ui.live.adapter.LiveRecyclerViewAdapter;
import com.cc.dc.ui.live.contract.LiveAllContract;
import com.cc.dc.ui.live.presenter.LiveAllPresenter;

import java.util.List;

/**
 * Created by dc on 2017/9/28.
 * 全部直播页面
 */
public class LiveAllFragment extends BaseListFragment<LiveAllPresenter> implements  LiveAllContract.View, LiveRecyclerViewAdapter.OnItemClickListener {

    @Override
    public void bindPresenter() {
        presenter = new LiveAllPresenter();
        presenter.attachView(this);
    }

    @Override
    public void refreshData() {
        presenter.loadLiveAllList(0, LIMIT, true);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void loadMoreData() {
        presenter.loadLiveAllList(data.size(), LIMIT, false);
    }

    @Override
    public void showLiveAllList(List<LiveBean> list, boolean isRefresh) {
        if (isRefresh) {
            data.clear();
            refreshLayout.setRefreshing(false);
        } else {
            recyclerView.loadingFinish();
        }
        data.addAll(list);
        adapter.notifyDataSetChanged();
        itemDecoration.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
        startActivity(intent);
    }
}
