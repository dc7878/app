package com.cc.dc.bean;

import android.text.TextUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.cc.dc.common.utils.LUtil;

/**
 * Created by dc on 2017/9/30.
 */
public class HomeCateInfoBean {

    private int level;

    private int cid;

    @JSONField(name = "cate2_name")
    private String cateName;

    @JSONField(name = "cate1_name")
    private String cate1Name;

    @JSONField(name = "icon_url")
    private String iconUrl;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCateName() {
        if (TextUtils.isEmpty(cateName) && !TextUtils.isEmpty(cate1Name)) {
            cateName = cate1Name;
        }
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCate1Name() {
        return cate1Name;
    }

    public void setCate1Name(String cate1Name) {
        this.cate1Name = cate1Name;
    }
}
