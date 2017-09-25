package com.cc.dc.ui.main.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.cc.dc.Constant;
import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.custom.NoAnimateViewPager;
import com.cc.dc.entity.TabEntity;
import com.cc.dc.dc.R;
import com.cc.dc.ui.main.adapter.MainViewPagerAdapter;
import com.cc.dc.ui.follow.fragment.FollowFragment;
import com.cc.dc.ui.main.fragment.HomeFragment;
import com.cc.dc.ui.live.fragment.LiveFragment;
import com.cc.dc.ui.main.fragment.HomeRecommend;
import com.cc.dc.ui.user.fragment.UserFragment;
import com.cc.dc.ui.video.fragment.VideoFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.common_tab_layout_main)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页", "直播", "视频", "关注", "我的"};
    private int[] mIconUnSelectIds = {
            R.mipmap.home_pressed, R.mipmap.live_pressed,
            R.mipmap.video_pressed, R.mipmap.follow_pressed, R.mipmap.user_pressed};
    private int[] mIconSelectIds = {
            R.mipmap.home_selected, R.mipmap.live_selected,
            R.mipmap.video_selected, R.mipmap.follow_selected, R.mipmap.user_selected};

    private ArrayList<CustomTabEntity> tabEntities;

    private HomeFragment homeFragment;
    private HomeRecommend[] homeRecommends;

    private final int count = 4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tabEntities = new ArrayList<>();
        for (int i = 0; i < count + 1; i++) {
            tabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        tabLayout.setTabData(tabEntities);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTab(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        initFragments(savedInstanceState);
    }

    private void initFragments(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
            homeRecommends = new HomeRecommend[count];
            for (int i = 0; i < homeRecommends.length; i ++) {
                homeRecommends[i] = (HomeRecommend) getSupportFragmentManager().findFragmentByTag("HomeRecommend" + i);
            }
            currentTabPosition = savedInstanceState.getInt(Constant.MAIN_CURRENT_POSITION);
        } else {
            homeFragment = new HomeFragment();
            transaction.add(R.id.container, homeFragment, "homeFragment");
            homeRecommends = new HomeRecommend[count];
            for (int i = 0; i < homeRecommends.length; i ++) {
                homeRecommends[i] = new HomeRecommend();
                transaction.add(R.id.container, homeRecommends[i], "homeRecommends" + i);
            }
        }
        transaction.commit();
        switchTab(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    private void switchTab(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                for (int i = 0; i < count; i++) {
                    transaction.hide(homeRecommends[i]);
                }
                transaction.show(homeFragment);
                break;
            default:
                transaction.hide(homeFragment);
                for (int i = 0; i < count; i++) {
                    if (i != position) {
                        transaction.hide(homeRecommends[i]);
                    }
                }
                transaction.show(homeRecommends[position]);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (tabLayout != null) {
            outState.putInt(Constant.MAIN_CURRENT_POSITION, tabLayout.getCurrentTab());
        }
    }
}
