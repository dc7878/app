package com.cc.dc.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by dc on 2017/9/27.
 */
public class LiveColumnBean {
    @JSONField(name = "cate_id")
    private String cateId;

    @JSONField(name = "cate_name")
    private String cateName;

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
