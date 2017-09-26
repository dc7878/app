package com.cc.dc.ui.live.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.custom.GridItemDecoration;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.dc.R;
import com.cc.dc.ui.live.contract.LiveContract;
import com.cc.dc.ui.live.presenter.LivePresenter;
import com.cc.dc.ui.live.adapter.LiveRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/19.
 */
public class LiveFragment extends BaseFragment<LivePresenter> implements LiveContract.View, LoadMoreRecyclerView.OnLoadMoreListener {

    @Bind(R.id.refresh_layout_live)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler_view_live)
    LoadMoreRecyclerView recyclerView;

    private List<LiveBean> data;
    private LiveRecyclerViewAdapter adapter;
    private GridItemDecoration itemDecoration;

    private int spanCount = 2;

    private final int LIMIT = 20;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        adapter = new LiveRecyclerViewAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
        itemDecoration = new GridItemDecoration.Builder()
                .eachEqual(20)
                .spanCount(spanCount)
                .data(data)
                .build();
        recyclerView.addItemDecoration(itemDecoration);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });

        recyclerView.setOnLoadMoreListener(this);
    }

    @Override
    public void initPresenter() {
        presenter = new LivePresenter();
        presenter.attachView(this);
    }

    @Override
    public void lazyLoadData() {
        LUtil.e("LiveFragment", "lazyLoadData");
        refreshLayout.setRefreshing(true);
        loadData(true);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLiveList(List<LiveBean> list, boolean isRefresh) {
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
    public void onLoadMore() {
        loadData(false);
    }

    private void loadData(boolean isRefresh) {
        if (isRefresh) {
            presenter.loadLiveList(0, LIMIT, isRefresh);
        } else {
            presenter.loadLiveList(data.size(), LIMIT, isRefresh);
        }
    }
}
