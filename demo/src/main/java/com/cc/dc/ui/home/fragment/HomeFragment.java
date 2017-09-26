package com.cc.dc.ui.home.fragment;

import com.cc.dc.bean.HomeCateBean;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.custom.ParentViewPager;
import com.cc.dc.dc.R;
import com.cc.dc.ui.home.adapter.HomeViewPagerAdapter;
import com.cc.dc.ui.home.contract.HomeCateContract;
import com.cc.dc.ui.home.presenter.HomePresenter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/19.
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeCateContract.View {

    @Bind(R.id.sliding_tab_layout_home)
    SlidingTabLayout tabLayout;
    @Bind(R.id.view_pager_home)
    ParentViewPager viewPager;

    private List<BaseFragment> fragments = new ArrayList<>();

    private String[] titles;

    private HomeViewPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {
        presenter = new HomePresenter();
        presenter.attachView(this);

        presenter.loadData();
    }

    @Override
    public void lazyLoadData() {
    }

    @Override
    public void showHomeCateList(List<HomeCateBean> cateList) {
        int count = cateList.size();
        titles = new String[count + 1];
        titles[0] = "推荐";
        for (int i = 0; i < count; i++) {
            titles[i + 1] = cateList.get(i).getTitle();
        }
        fragments.add(new HomeRecommend());
        for (int i = 0; i < count; i++) {
            fragments.add(new HomeOtherFragment());
        }

        adapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        tabLayout.setViewPager(viewPager, titles);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
