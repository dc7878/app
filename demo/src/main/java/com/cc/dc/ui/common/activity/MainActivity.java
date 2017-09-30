package com.cc.dc.ui.common.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.cc.dc.Constant;
import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.entity.TabEntity;
import com.cc.dc.dc.R;
import com.cc.dc.ui.follow.fragment.FollowFragment;
import com.cc.dc.ui.home.fragment.HomeFragment;
import com.cc.dc.ui.live.fragment.LiveFragment;
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

    private String[] mTitles = {"首页", "直播", "关注", "发现", "我的"};
    private int[] mIconUnSelectIds = {
            R.mipmap.home_pressed, R.mipmap.live_pressed, R.mipmap.follow_pressed,
            R.mipmap.video_pressed, R.mipmap.user_pressed};
    private int[] mIconSelectIds = {
            R.mipmap.home_selected, R.mipmap.live_selected, R.mipmap.follow_selected,
            R.mipmap.video_selected, R.mipmap.user_selected};

    private ArrayList<CustomTabEntity> tabEntities;

    private HomeFragment homeFragment;
    private LiveFragment liveFragment;
    private VideoFragment videoFragment;
    private FollowFragment followFragment;
    private UserFragment userFragment;

    private final int TAB_SIZE = 5;

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
        for (int i = 0; i < TAB_SIZE; i++) {
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
            liveFragment = (LiveFragment) getSupportFragmentManager().findFragmentByTag("liveFragment");
            followFragment = (FollowFragment) getSupportFragmentManager().findFragmentByTag("followFragment");
            videoFragment = (VideoFragment) getSupportFragmentManager().findFragmentByTag("videoFragment");
            userFragment = (UserFragment) getSupportFragmentManager().findFragmentByTag("userFragment");
            currentTabPosition = savedInstanceState.getInt(Constant.MAIN_CURRENT_POSITION);
        } else {
            homeFragment = new HomeFragment();
            liveFragment = new LiveFragment();
            followFragment = new FollowFragment();
            videoFragment = new VideoFragment();
            userFragment = new UserFragment();

            transaction.add(R.id.container, homeFragment, "homeFragment");
            transaction.add(R.id.container, liveFragment, "liveFragment");
            transaction.add(R.id.container, followFragment, "followFragment");
            transaction.add(R.id.container, videoFragment, "videoFragment");
            transaction.add(R.id.container, userFragment, "userFragment");
        }
        transaction.commit();
        switchTab(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    private void switchTab(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.hide(liveFragment);
                transaction.hide(videoFragment);
                transaction.hide(followFragment);
                transaction.hide(userFragment);
                transaction.show(homeFragment);
                break;
            case 1:
                transaction.hide(homeFragment);
                transaction.hide(videoFragment);
                transaction.hide(followFragment);
                transaction.hide(userFragment);
                transaction.show(liveFragment);
                break;
            case 2:
                transaction.hide(homeFragment);
                transaction.hide(liveFragment);
                transaction.hide(videoFragment);
                transaction.hide(userFragment);
                transaction.show(followFragment);
                break;
            case 3:
                transaction.hide(homeFragment);
                transaction.hide(liveFragment);
                transaction.hide(followFragment);
                transaction.hide(userFragment);
                transaction.show(videoFragment);
                break;
            case 4:
                transaction.hide(homeFragment);
                transaction.hide(liveFragment);
                transaction.hide(videoFragment);
                transaction.hide(followFragment);
                transaction.show(userFragment);
                break;
            default:
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
