package com.cc.dc.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.cc.dc.common.bean.MultipleColumnBean;

/**
 * Created by dc on 2017/9/19.
 */
public class LiveBean extends MultipleColumnBean {

    @JSONField(name = "room_id")
    private String roomId;

    @JSONField(name = "room_name")
    private String name;

    private String avatar;

    @JSONField(name = "room_src")
    private String roomSrc;

    @JSONField(name = "online_num")
    private int online;

    @JSONField(name = "nickname")
    private String nickName;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoomSrc() {
        return roomSrc;
    }

    public void setRoomSrc(String roomSrc) {
        this.roomSrc = roomSrc;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
