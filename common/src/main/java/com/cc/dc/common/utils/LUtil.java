package com.cc.dc.common.utils;

import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by dc on 2017/9/21.
 */

public class LUtil {

    static {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void e(String tag, String msg) {
//        Logger.t(tag).e(msg);
        Log.e(tag, msg);
    }
}
