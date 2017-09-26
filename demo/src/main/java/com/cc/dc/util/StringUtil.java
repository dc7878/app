package com.cc.dc.util;


import java.text.DecimalFormat;

/**
 * Created by dc on 2017/9/26.
 */
public class StringUtil {

    /**
     * 人数大于10000万，转成万为单位保留两位小数
     * @param online
     */
    public static String getOnlineStr(int online) {
        String onlineStr;
        if (online > 10000) {
            double number = (double) online / 10000;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            onlineStr = decimalFormat.format(number) + "万";
        } else {
            onlineStr = String.valueOf(online);
        }
        return onlineStr;
    }
}
