package com.cc.dc.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by dc on 2017/9/30.
 */
public class HomeHotDataBean {

    @JSONField(name = "room_list")
    private List<HomeCateListBean> roomList;

    @JSONField(name = "icon_url")
    private String iconUrl;

    @JSONField(name = "tag_name")
    private String cateName;

    public List<HomeCateListBean> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<HomeCateListBean> roomList) {
        this.roomList = roomList;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
