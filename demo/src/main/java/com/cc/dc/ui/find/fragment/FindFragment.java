package com.cc.dc.ui.find.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.cc.dc.bean.VideoBean;
import com.cc.dc.common.custom.GridItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.dc.R;
import com.cc.dc.ui.find.adapter.FindRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dc on 2017/9/19.
 */
public class FindFragment extends BaseFragment {

    @Bind(R.id.civ_yb)
    CircleImageView civYuba;
    @Bind(R.id.civ_video)
    CircleImageView civVideo;
    @Bind(R.id.civ_lrs)
    CircleImageView civLRS;
    @Bind(R.id.civ_hot)
    CircleImageView civHot;

    @Bind(R.id.refresh_layout_video)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler_view_video)
    LoadMoreRecyclerView recyclerViewHome;

    private List<VideoBean> data = new ArrayList<>();
    private FindRecyclerViewAdapter adapter;
    private GridItemDecoration itemDecoration;

    private int spanCount = 2;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civYuba);
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civVideo);
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civLRS);
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civHot);


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        adapter = new FindRecyclerViewAdapter(getActivity(), data);
        recyclerViewHome.setAdapter(adapter);
        recyclerViewHome.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
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
