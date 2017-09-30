package com.cc.dc.bean;

import java.util.List;

/**
 * Created by dc on 2017/9/30.
 */
public class HomeCateDataBean {

    private HomeCateInfoBean cateInfo;

    private List<HomeCateListBean> list;

    public HomeCateInfoBean getCateInfo() {
        return cateInfo;
    }

    public void setCateInfo(HomeCateInfoBean cateInfo) {
        this.cateInfo = cateInfo;
    }

    public List<HomeCateListBean> getList() {
        return list;
    }

    public void setList(List<HomeCateListBean> list) {
        this.list = list;
    }
}
