package com.cc.dc.ui.live.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.bean.LiveGameCateBean;
import com.cc.dc.common.custom.GridItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.dc.R;
import com.cc.dc.ui.live.adapter.LiveGameCateAdapter;
import com.cc.dc.ui.live.adapter.LiveRecyclerViewAdapter;
import com.cc.dc.ui.live.contract.LiveCateContract;
import com.cc.dc.ui.live.presenter.LiveCatePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/27.
 * 直播Item中的每一个页面(不包括常用页面)
 */
public class LiveCateFragment extends BaseFragment<LiveCatePresenter> implements LiveCateContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.OnLoadMoreListener {

    @Bind(R.id.recycler_game_list)
    RecyclerView recyclerViewGame;
    @Bind(R.id.refresh_layout_live_cate)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler_view_live_cate)
    LoadMoreRecyclerView recyclerView;

    private List<LiveBean> data;
    private LiveRecyclerViewAdapter adapter;
    private GridItemDecoration itemDecoration;

    private List<LiveGameCateBean> liveGameCateBeans;
    private LiveGameCateAdapter gameCateAdapter;

    private int spanCount = 2;
    private final int LIMIT = 20;

    private String cateId;
    private int level;
    private String shortName;

    public static LiveCateFragment getInstance(String cateId, int level, String shortName) {
        LiveCateFragment fragment = new LiveCateFragment();
        fragment.cateId = cateId;
        fragment.level = level;
        fragment.shortName = shortName;
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_cate;
    }

    @Override
    public void initView() {
        if (level <= 1) {
            initGameCate();
        }

        refreshLayout.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);

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
    }

    @Override
    public void initPresenter() {
        presenter = new LiveCatePresenter();
        presenter.attachView(this);
    }

    @Override
    public void lazyLoadData() {
        if (level <= 1) {
            LUtil.e("LiveCateFragmentInfo", "LiveCateFragmentInfo>>>" + shortName);
            presenter.loadGameCateList(shortName);
        }
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

    @Override
    public void showLiveGameCateList(List<LiveGameCateBean> list) {
        if (list.size() <= 0) {
            recyclerViewGame.setVisibility(View.GONE);
        }
        LUtil.e("LiveCateFragmentInfo", "LiveCateFragmentInfo>>>" + list.size());
        liveGameCateBeans.addAll(list);
        gameCateAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLiveCateList(List<LiveBean> list, boolean isRefresh) {
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

    private void initGameCate() {
        recyclerViewGame.setVisibility(View.VISIBLE);
        liveGameCateBeans = new ArrayList<>();
        gameCateAdapter = new LiveGameCateAdapter(getActivity(), liveGameCateBeans);
        recyclerViewGame.setAdapter(gameCateAdapter);
        recyclerViewGame.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }

    private void loadData(boolean isRefresh) {
        if (isRefresh) {
            presenter.loadCateLiveList(level, cateId, 0, LIMIT, isRefresh);
        } else {
            presenter.loadCateLiveList(level, cateId, data.size(), LIMIT, isRefresh);
        }
    }
}
