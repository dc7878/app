package com.cc.dc.ui.live.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.dc.R;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/27.
 * 直播Item中的每一个页面(不包括常用页面)
 */
public class LiveCateFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.OnLoadMoreListener {

    @Bind(R.id.refresh_layout_live_cate)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler_view_live_cate)
    LoadMoreRecyclerView recyclerView;

    private String cateId;

    public static LiveCateFragment getInstance(String cateId) {
        LiveCateFragment fragment = new LiveCateFragment();
        fragment.cateId = cateId;
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_cate;
    }

    @Override
    public void initView() {
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);
    }

    @Override
    public void lazyLoadData() {
        LUtil.e("LiveCateFragmentInfo", "cateId>>>" + cateId);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
