package com.cc.dc.ui.find.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cc.dc.bean.VideoBean;
import com.cc.dc.bean.find.FindDigestBean;
import com.cc.dc.bean.find.FindTopicBean;
import com.cc.dc.bean.find.TopicMessageBean;
import com.cc.dc.common.custom.GridItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.custom.ParentViewPager;
import com.cc.dc.dc.R;
import com.cc.dc.ui.find.adapter.FindRecyclerViewAdapter;
import com.cc.dc.ui.find.contract.FindContract;
import com.cc.dc.ui.find.presenter.FindPresenter;
import com.cc.dc.ui.home.adapter.HomeViewPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dc on 2017/9/19.
 */
public class FindFragment extends BaseFragment<FindPresenter> implements FindContract.View {

    @Bind(R.id.civ_yb)
    CircleImageView civYuBa;
    @Bind(R.id.civ_video)
    CircleImageView civVideo;
    @Bind(R.id.civ_lrs)
    CircleImageView civLRS;
    @Bind(R.id.civ_hot)
    CircleImageView civHot;

    @Bind(R.id.layout_info)
    LinearLayout layoutInfo;
    @Bind(R.id.tv_info_1)
    TextView tvInfo1;
    @Bind(R.id.tv_info_2)
    TextView tvInfo2;
    @Bind(R.id.tv_info_3)
    TextView tvInfo3;
    @Bind(R.id.tv_info_4)
    TextView tvInfo4;
    @Bind(R.id.riv_info_1)
    RoundedImageView ivInfo1;
    @Bind(R.id.riv_info_2)
    RoundedImageView ivInfo2;
    @Bind(R.id.riv_info_3)
    RoundedImageView ivInfo3;
    @Bind(R.id.riv_info_4)
    RoundedImageView ivInfo4;

    @Bind(R.id.refresh_layout_video)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler_view_video)
    LoadMoreRecyclerView recyclerViewHome;

    @Bind(R.id.sliding_tab_layout_find)
    SlidingTabLayout tabLayout;
    @Bind(R.id.view_pager_find)
    ParentViewPager viewPager;

    private List<VideoBean> data = new ArrayList<>();
    private FindRecyclerViewAdapter adapter;
    private GridItemDecoration itemDecoration;
    private int spanCount = 2;

    private List<BaseFragment> fragments = new ArrayList<>();
    private String[] titles;
    private HomeViewPagerAdapter pagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civYuBa);
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

        initPagerAdapter();
    }

    @Override
    public void initPresenter() {
        presenter = new FindPresenter();
        presenter.attachView(this);
    }

    @Override
    public void lazyLoadData() {
        refreshLayout.setRefreshing(true);
        refresh();

        presenter.loadTopicMessageList();
        presenter.loadDigestList(0);
        presenter.loadTopicList(0);
    }

    @Override
    public void showTopicMessageList(List<TopicMessageBean> list) {
        if (list.size() >= 3) {
            layoutInfo.setVisibility(View.VISIBLE);
        } else {
            return;
        }
        tvInfo1.setText(list.get(0).getName());
        tvInfo2.setText(list.get(1).getName());
        tvInfo3.setText(list.get(2).getName());
        tvInfo4.setText(getResources().getString(R.string.find_more_hot_talk));

        Glide.with(this).load(list.get(0).getAvatar()).into(ivInfo1);
        Glide.with(this).load(list.get(1).getAvatar()).into(ivInfo2);
        Glide.with(this).load(list.get(2).getAvatar()).into(ivInfo3);
    }

    @Override
    public void showDigestList(List<FindDigestBean> list) {
        LUtil.e("FindFragment", "showDigestList>>>" + list.size());
    }

    @Override
    public void showTopicList(List<FindTopicBean> list) {
        LUtil.e("FindFragment", "showTopicList>>>" + list.size());
    }

    private void initPagerAdapter() {
        titles = new String[4];
        titles[0] = "精选";
        titles[1] = "榜单";
        titles[2] = "鱼塘";
        titles[3] = "小组";
        for (int i = 0; i < 4; i++) {
            fragments.add(new FindDigestListFragment());
        }
        pagerAdapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setViewPager(viewPager, titles);
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
