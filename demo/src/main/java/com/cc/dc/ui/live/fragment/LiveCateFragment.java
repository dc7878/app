package com.cc.dc.ui.live.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.bean.LiveGameCateBean;
import com.cc.dc.common.custom.GridItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.utils.DensityUtil;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.dc.R;
import com.cc.dc.ui.live.adapter.LiveGameCateAdapter;
import com.cc.dc.ui.live.adapter.LiveRecyclerViewAdapter;
import com.cc.dc.ui.live.contract.LiveCateContract;
import com.cc.dc.ui.live.presenter.LiveCatePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by dc on 2017/9/27.
 * 直播Item中的每一个页面(不包括常用页面)
 */
public class LiveCateFragment extends BaseFragment<LiveCatePresenter> implements LiveCateContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.OnLoadMoreListener, LoadMoreRecyclerView.OnScrollStateChanged {

    @Bind(R.id.layout_game_cate)
    LinearLayout layoutGame;
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
    private final int gameColumnCount = 3;

    private int spanCount = 2;
    private final int LIMIT = 20;

    private String cateId;
    private int level;
    private String shortName;

    private boolean isShowAll = false;

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
        // 默认显示一行
        LinearLayout.LayoutParams paramsRecycler = (LinearLayout.LayoutParams) recyclerViewGame.getLayoutParams();
        paramsRecycler.height = DensityUtil.dip2px(getActivity(), 40) * 1;

        liveGameCateBeans.addAll(list);
        gameCateAdapter.notifyDataSetChanged();
        if (list.size() > 1) {
            // 至少有一行
            layoutGame.setVisibility(View.VISIBLE);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) refreshLayout.getLayoutParams();
            params.setMargins(0, DensityUtil.dip2px(getActivity(), 70), 0, 0);
        }
    }

    @Override
    public void showLiveCateList(List<LiveBean> list, boolean isRefresh) {
        if (isRefresh) {
            recyclerView.setOnScrollStateChanged(this);
            data.clear();
            refreshLayout.setRefreshing(false);
        } else {
            recyclerView.loadingFinish();
        }
        data.addAll(list);
        adapter.notifyDataSetChanged();
        itemDecoration.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_change)
    protected void hideOrShow() {
        isShowAll = !isShowAll;
        // isShowAll为false时显示一行
        int showLine = 1;
        int totalLine = liveGameCateBeans.size() / gameColumnCount + (liveGameCateBeans.size() % gameColumnCount == 0 ? 0 : 1);
        if (isShowAll) {
            // 显示最多行
            showLine = totalLine > 6 ? 6 : totalLine;
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerViewGame.getLayoutParams();
        params.height = DensityUtil.dip2px(getActivity(), 40) * showLine;
        recyclerViewGame.requestLayout();
    }

    @Override
    public void scrollStateChanged() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerViewGame.getLayoutParams();
        params.height = DensityUtil.dip2px(getActivity(), 40) * 1;
        recyclerViewGame.requestLayout();
        isShowAll = false;
    }

    private void initGameCate() {
        layoutGame.setVisibility(View.GONE);
        liveGameCateBeans = new ArrayList<>();
        gameCateAdapter = new LiveGameCateAdapter(getActivity(), liveGameCateBeans);
        recyclerViewGame.setAdapter(gameCateAdapter);
        recyclerViewGame.setLayoutManager(new StaggeredGridLayoutManager(gameColumnCount, StaggeredGridLayoutManager.VERTICAL));

        gameCateAdapter.setOnItemOnClickListener(new LiveGameCateAdapter.OnItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
                LUtil.e("LiveCateFragmentInfo", "LiveCateFragmentInfo>>>" + liveGameCateBeans.get(position).getTagName());
            }
        });
    }

    private void loadData(boolean isRefresh) {
        if (isRefresh) {
            presenter.loadCateLiveList(level, cateId, 0, LIMIT, isRefresh);
        } else {
            presenter.loadCateLiveList(level, cateId, data.size(), LIMIT, isRefresh);
        }
    }
}
