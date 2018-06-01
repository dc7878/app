package com.cc.dc.kotlindemo.bean

import com.alibaba.fastjson.annotation.JSONField

/**
 * Created by dc on 2018/6/1.
 */
class NewsBean {

    @JSONField(name = "rtype")
    var rType: String? = null

    @JSONField(name = "rvalue")
    var rValue: String? = null

    var nid: String? = null

    var tid: String? = null

    var title: String? = null

    @JSONField(name = "copyfrom")
    var copyFrom: String? = null

    @JSONField(name = "update_time")
    var updateTime: String? = null
}