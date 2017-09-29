package com.cc.dc.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by dc on 2017/9/29.
 */
public class LiveGameCateBean {

    @JSONField(name = "tag_id")
    private String tagId;

    @JSONField(name = "tag_name")
    private String tagName;

    @JSONField(name = "short_name")
    private String shortName;

    @JSONField(name = "cate_id")
    private String cateId;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
}
