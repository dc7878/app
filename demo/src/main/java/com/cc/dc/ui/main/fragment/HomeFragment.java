package com.cc.dc.ui.main.fragment;

import android.support.v4.view.ViewPager;

import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.dc.R;
import com.cc.dc.ui.main.adapter.HomeViewPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/19.
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.sliding_tab_layout_home)
    SlidingTabLayout tabLayout;
    @Bind(R.id.view_pager_home)
    ViewPager viewPager;

    private List<BaseFragment> fragments = new ArrayList<>();

    private String[] titles = new String[]{"推荐", "深度", "快讯"};

    private HomeViewPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        fragments.add(new HomeRecommend());
        fragments.add(new HomeOtherFragment());
        fragments.add(new HomeOtherFragment());

        adapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        tabLayout.setViewPager(viewPager, titles);
    }

    @Override
    public void lazyLoadData() {
    }
}
