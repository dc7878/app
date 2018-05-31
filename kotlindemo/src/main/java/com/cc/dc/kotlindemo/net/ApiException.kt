package com.cc.dc.kotlindemo.net

/**
 * Created by dc on 2017/7/7.
 * 统一的网络错误
 */

class ApiException(private val errorCode: Int, private val errorMsg: String?) : Exception(errorMsg)
