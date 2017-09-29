package com.cc.dc.ui.live.fragment;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.ui.common.fragment.BaseListFragment;
import com.cc.dc.ui.live.contract.LiveAllContract;
import com.cc.dc.ui.live.presenter.LiveAllPresenter;

import java.util.List;

/**
 * Created by dc on 2017/9/28.
 * 全部直播页面
 */
public class LiveAllFragment extends BaseListFragment<LiveAllPresenter> implements  LiveAllContract.View {

    @Override
    public void bindPresenter() {
        presenter = new LiveAllPresenter();
        presenter.attachView(this);
    }

    @Override
    public void refreshData() {
        presenter.loadLiveAllList(0, LIMIT, true);
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
}
