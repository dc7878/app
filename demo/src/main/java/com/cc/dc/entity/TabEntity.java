package com.cc.dc.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by dc on 2017/9/18.
 */

public class TabEntity implements CustomTabEntity {

    private String tabTitle;

    private int tabSelectedIcon;

    private int tabUnSelectedIcon;

    public TabEntity(String tabTitle, int tabSelectedIcon, int tabUnSelectedIcon) {
        this.tabTitle = tabTitle;
        this.tabSelectedIcon = tabSelectedIcon;
        this.tabUnSelectedIcon = tabUnSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return tabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return tabSelectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return tabUnSelectedIcon;
    }
}
