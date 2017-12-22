package com.cc.dc.ndk;

/**
 * Created by dc on 2017/12/22.
 */

public class NDKTest {

    static {
        System.loadLibrary("ndkTest");
    }

    public native String getStringInfo();
}
