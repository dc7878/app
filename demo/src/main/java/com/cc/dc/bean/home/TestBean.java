package com.cc.dc.bean.home;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by dc on 2017/10/30.
 */

public class TestBean {

    @JSONField(name = "rtmp_url")
    private String rtmpUrl;

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }
}
