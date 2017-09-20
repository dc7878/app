package com.cc.dc.bean;

import com.cc.dc.common.bean.MultipleColumnBean;

/**
 * Created by dc on 2017/9/20.
 */
public class VideoBean extends MultipleColumnBean {

    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
