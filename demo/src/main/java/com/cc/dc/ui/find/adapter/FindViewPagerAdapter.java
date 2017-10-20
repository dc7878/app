package com.cc.dc.ui.find.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cc.dc.common.ui.BaseFragment;

import java.util.List;


/**
 * Created by dc on 2017/9/21.
 */
public class FindViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> titles;

    public FindViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
