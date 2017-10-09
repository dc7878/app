package com.cc.dc.common.utils;

import android.content.Context;

/**
 * Created by dc on 2017/10/9.
 */

public class ResourceUtil {

    /**
     * 通过图片的名称，获取图片在drawable下的id
     * @param context
     * @param imageName
     */
    public static int  getResource(Context context, String imageName) {
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return resId;
    }
}
