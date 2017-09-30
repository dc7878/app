package com.cc.dc.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by dc on 2017/9/30.
 */
public class HomeCateListBean {

    @JSONField(name = "game_name")
    private String gameName;

    private int online;

    @JSONField(name = "room_name")
    private String roomName;

    @JSONField(name = "room_id")
    private String roomId;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
