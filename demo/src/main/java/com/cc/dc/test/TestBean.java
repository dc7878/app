package com.cc.dc.test;

import com.cc.dc.common.bean.MultipleColumnBean;

/**
 * Created by dc on 2017/11/2.
 */
public class TestBean extends MultipleColumnBean{

    private int type;
    private String title;
    private int imageId;
    private String imageUrl;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public interface TYPE {
        int TYPE_TITLE = 0x01;
        int TYPE_IMAGE = 0x02;
        int TYPE_MORE = 0x03;
    }
}
