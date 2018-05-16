package com.cc.dc.customview.marquee.test;

/**
 * Created by dc on 2018/3/30.
 */

public class ADEnity {

    private String mFront ;
    private String mBack ;
    private String mUrl ;

    public ADEnity() {
    }

    public ADEnity(String mFront, String mBack, String mUrl) {
        this.mFront = mFront;
        this.mBack = mBack;
        this.mUrl = mUrl;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmFront() {
        return mFront;
    }

    public void setmFront(String mFront) {
        this.mFront = mFront;
    }

    public String getmBack() {
        return mBack;
    }

    public void setmBack(String mBack) {
        this.mBack = mBack;
    }
}
