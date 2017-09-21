package com.cc.dc.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cc.dc.common.ui.BaseFragment;

import java.util.List;


/**
 * Created by dc on 2017/9/21.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public HomeViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
