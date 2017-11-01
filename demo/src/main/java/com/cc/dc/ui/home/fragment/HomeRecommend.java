package com.cc.dc.ui.home.fragment;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cc.dc.bean.HomeCateDataBean;
import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.custom.ViewPagerAdapter;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.common.utils.StringUtil;
import com.cc.dc.R;
import com.cc.dc.ui.home.adapter.HomeRecommendAdapter;
import com.cc.dc.ui.home.contract.HomeRecommendContract;
import com.cc.dc.ui.home.presenter.HomeRecommendPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/21.
 * 首页推荐
 */
public class HomeRecommend extends BaseFragment<HomeRecommendPresenter> implements HomeRecommendContract.View {

    @Bind(R.id.refresh_layout_recommend)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.view_pager_slider)
    ViewPager slider;
    @Bind(R.id.recycler_view_recommend)
    LoadMoreRecyclerView recyclerView;

    private List<ImageView> imageViews = new ArrayList<>();
    private ViewPagerAdapter pagerAdapter;

    private HomeRecommendAdapter adapter;
    private List<Object> data = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {
        adapter = new HomeRecommendAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initPresenter() {
        presenter = new HomeRecommendPresenter();
        presenter.attachView(this);
    }

    @Override
    public void lazyLoadData() {
        presenter.loadSliderList();

        presenter.loadBigDataRoomList("android1", "", StringUtil.getAuth32());
        presenter.loadHomeCustomList();

        presenter.loadHotCateList("android1", "", StringUtil.getAuth32());
    }

    @Override
    public void showSliderList(List<HomeRecommendSliderBean> list) {
        for (HomeRecommendSliderBean bean: list) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(bean.getPicUrl()).into(imageView);
            imageViews.add(imageView);
        }
        pagerAdapter = new ViewPagerAdapter(imageViews);
        slider.setAdapter(pagerAdapter);
    }

    @Override
    public void showBigDataRoomList(List<HomeCateDataBean> list) {
        LUtil.e("HomeRecommend", "showBigDataRoomList>>" + list.size());
    }

    @Override
    public void showHomeCustomList(List<HomeCateDataBean> list) {
        LUtil.e("HomeRecommend", "showHomeCustomList>>" + list.size());
    }

    @Override
    public void showHotCateList(List<HomeCateDataBean> list) {
        LUtil.e("HomeRecommend", "showHotCateList>>" + list.size());
    }
}
