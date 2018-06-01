package com.cc.dc.kotlindemo.listener

/**
 * Created by dc on 2018/3/6.
 */

interface ViewStatusListener {

    fun showEmpty()

    fun showLoading()

    fun showNormal()

    fun showNetError()
}
