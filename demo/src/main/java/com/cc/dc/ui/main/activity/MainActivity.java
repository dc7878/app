package com.cc.dc.ui.main.activity;

import android.support.v4.view.ViewPager;

import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.custom.NoAnimateViewPager;
import com.cc.dc.entity.TabEntity;
import com.cc.dc.dc.R;
import com.cc.dc.ui.main.adapter.MainViewPagerAdapter;
import com.cc.dc.ui.follow.fragment.FollowFragment;
import com.cc.dc.ui.main.fragment.HomeFragment;
import com.cc.dc.ui.live.fragment.LiveFragment;
import com.cc.dc.ui.user.fragment.UserFragment;
import com.cc.dc.ui.video.fragment.VideoFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.view_pager_main)
    NoAnimateViewPager viewPager;
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

    private List<BaseFragment> fragments = new ArrayList<>();
    private MainViewPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        tabEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        tabLayout.setTabData(tabEntities);

        fragments.add(new HomeFragment());
        fragments.add(new LiveFragment());
        fragments.add(new VideoFragment());
        fragments.add(new FollowFragment());
        fragments.add(new UserFragment());

        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
