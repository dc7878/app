package com.cc.dc.memorytraining.bean;

import com.cc.dc.common.bean.MultipleColumnBean;

/**
 * Created by dc on 2017/10/9.
 */
public class NumberCodeBean extends MultipleColumnBean {

    private int  resId;

    private String desc;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
