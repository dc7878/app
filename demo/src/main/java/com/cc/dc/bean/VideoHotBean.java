package com.cc.dc.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by dc on 2017/9/26.
 */
public class VideoHotBean {

    @JSONField(name = "nickname")
    private String nickName;

    @JSONField(name = "video_title")
    private String videoTitle;

    @JSONField(name = "video_cover")
    private String videoCover;

    @JSONField(name = "view_num")
    private String viewNum;
}
