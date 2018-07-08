package com.cc.dc.kotlindemo.bean.aacoin

/**
 * Created by dc on 18/7/1.
 */
class DetailBean {

    // 交易市场
    lateinit var symbol: String

    // 24小时最高价
    lateinit var highest: String

    // 24小时最低价
    lateinit var lowest: String

    // 最新价
    lateinit var current: String

    // 24小时成交量
    lateinit var totalTradeAmount: String

    // 24小时前的开盘
    lateinit var price24HourBefore: String

    // 时间（例：2018-03-04 16:21:24）
    lateinit var time: String
}