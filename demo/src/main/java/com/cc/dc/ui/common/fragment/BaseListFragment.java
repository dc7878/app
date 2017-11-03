package com.cc.dc.ui.common.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.common.custom.StaggeredItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.presenter.BasePresenter;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.R;
import com.cc.dc.ui.live.adapter.LiveRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/29.
 */

public abstract class BaseListFragment<P extends BasePresenter> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.OnLoadMoreListener {

    @Bind(R.id.refresh_layout_list)
    protected SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler_view_list)
    protected LoadMoreRecyclerView recyclerView;

    protected List<LiveBean> data;
    protected LiveRecyclerViewAdapter adapter;
    protected StaggeredItemDecoration itemDecoration;

    protected P presenter;

    protected int spanCount = 2;
    protected final int LIMIT = 20;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView() {
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);

        data = new ArrayList<>();
        adapter = new LiveRecyclerViewAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
        itemDecoration = new StaggeredItemDecoration.Builder()
                .eachEqual(20)
                .spanCount(spanCount)
                .data(data)
                .build();
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void initPresenter() {
        bindPresenter();
    }

    @Override
    public void lazyLoadData() {
        refreshLayout.setRefreshing(true);
        loadData(true);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        loadData(false);
    }

    private void loadData(boolean isRefresh) {
        if (isRefresh) {
            refreshData();
        } else {
            loadMoreData();
        }
    }

    public abstract void bindPresenter();

    public abstract void refreshData();

    public abstract void loadMoreData();
}
