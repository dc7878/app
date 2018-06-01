package com.cc.dc.kotlindemo.bean

import com.alibaba.fastjson.annotation.JSONField

/**
 * Created by dc on 2018/6/1.
 * channel bean
 */
open class ChannelBean {

    @JSONField(name = "cnname")
    var name: String? = null

    var tid: String? = null
}