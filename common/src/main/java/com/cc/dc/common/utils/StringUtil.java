package com.cc.dc.common.utils;

import java.util.UUID;

/**
 * Created by dc on 2017/9/18.
 */
public class StringUtil {

    /**
     * 生成32auth数据
     * @return
     */
    public static String getAuth32() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
