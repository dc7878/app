package com.cc.dc.bean.find;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by dc on 2017/10/13.
 * 榜单
 */
public class FindTopicBean {

    @JSONField(name = "nick_name")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
