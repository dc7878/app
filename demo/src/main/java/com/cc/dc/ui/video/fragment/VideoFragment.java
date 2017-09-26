package com.cc.dc.ui.video.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cc.dc.bean.VideoBean;
import com.cc.dc.common.custom.GridItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.dc.R;
import com.cc.dc.ui.main.adapter.VideoRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/19.
 */
public class VideoFragment extends BaseFragment {

    @Bind(R.id.refresh_layout_video)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler_view_video)
    LoadMoreRecyclerView recyclerViewHome;

    private List<VideoBean> data = new ArrayList<>();
    private VideoRecyclerViewAdapter adapter;
    private GridItemDecoration itemDecoration;

    private int spanCount = 2;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        adapter = new VideoRecyclerViewAdapter(getActivity(), data);
        recyclerViewHome.setAdapter(adapter);
        recyclerViewHome.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
//        recyclerViewHome.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        itemDecoration = new GridItemDecoration.Builder()
                .leftSpace(10)
                .rightSpace(10)
                .rowSpace(20)
                .columnSpace(20)
                .topSpace(0)
                .bottomSpace(20)
                .spanCount(spanCount)
                .data(data)
                .build();
        recyclerViewHome.addItemDecoration(itemDecoration);
//        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerViewHome.addItemDecoration(new LinearItemDecoration(getActivity(), R.drawable.item_decoration_video, LinearItemDecoration.VERTICAL));
        recyclerViewHome.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void lazyLoadData() {
        refreshLayout.setRefreshing(true);
        refresh();
    }

    private void refresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                data.clear();

                for (int i = 0; i < 50; i++) {
                    VideoBean bean = new VideoBean();
                    bean.setName("name->" + i);
                    bean.setDesc("desc-->" + i * 10);
                    data.add(bean);
                }
                adapter.notifyDataSetChanged();
                itemDecoration.notifyDataSetChanged();
            }
        }, 2000);
    }

    public void loadMore() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i ++) {
                    VideoBean bean = new VideoBean();
                    bean.setName("加载更多name->" + data.size());
                    bean.setDesc("加载更多desc-->" + data.size());
                    data.add(bean);
                }
                adapter.notifyDataSetChanged();
                itemDecoration.notifyDataSetChanged();
                recyclerViewHome.loadingFinish();
            }
        }, 500);
    }
}
