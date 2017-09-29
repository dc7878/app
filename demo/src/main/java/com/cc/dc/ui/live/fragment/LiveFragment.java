package com.cc.dc.ui.live.fragment;

import com.cc.dc.bean.LiveColumnBean;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.custom.ParentViewPager;
import com.cc.dc.dc.R;
import com.cc.dc.ui.home.adapter.HomeViewPagerAdapter;
import com.cc.dc.ui.live.contract.LiveContract;
import com.cc.dc.ui.live.presenter.LivePresenter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/19.
 */
public class LiveFragment extends BaseFragment<LivePresenter> implements LiveContract.View {

    @Bind(R.id.sliding_tab_layout_live)
    SlidingTabLayout tabLayout;
    @Bind(R.id.view_pager_live)
    ParentViewPager viewPager;

    private List<BaseFragment> fragments = new ArrayList<>();

    private String[] titles;
    private HomeViewPagerAdapter homeViewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initPresenter() {
        presenter = new LivePresenter();
        presenter.attachView(this);
    }

    @Override
    public void lazyLoadData() {
        presenter.loadLiveColumnList();
    }


    @Override
    public void showLiveColumnList(List<LiveColumnBean> list) {
        int count = list.size();
        titles = new String[count + 2];
        titles[0] = "常用";
        titles[1] = "全部";
        for (int i = 0; i < count; i++) {
            titles[i + 2] = list.get(i).getCateName();
        }
        fragments.add(new LiveUsedFragment());
        fragments.add(new LiveAllFragment());
        for (int i = 0; i < count; i++) {
            fragments.add(LiveCateFragment.getInstance(list.get(i).getCateId(), list.get(i).getLevel(), list.get(i).getShortName()));
        }

        homeViewPagerAdapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(homeViewPagerAdapter);

        tabLayout.setViewPager(viewPager, titles);
    }
}
