package com.cc.dc.ui.home.fragment;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cc.dc.bean.HomeRecommendSliderBean;
import com.cc.dc.common.custom.ViewPagerAdapter;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.utils.DensityUtil;
import com.cc.dc.dc.R;
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

    @Bind(R.id.view_pager_slider)
    ViewPager slider;

    private List<ImageView> data = new ArrayList<>();
    private ViewPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {
        presenter = new HomeRecommendPresenter();
        presenter.attachView(this);

    }

    @Override
    public void lazyLoadData() {
        presenter.loadSliderList();
    }

    @Override
    public void showSliderList(List<HomeRecommendSliderBean> list) {
        for (HomeRecommendSliderBean bean: list) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(bean.getPicUrl()).override(1000, DensityUtil.dip2px(getActivity(), 150)).into(imageView);
            data.add(imageView);
        }
        adapter = new ViewPagerAdapter(data);
        slider.setAdapter(adapter);
    }
}
