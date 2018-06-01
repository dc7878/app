package com.cc.dc.kotlindemo

import android.app.Application

/**
 * Created by dc on 2018/5/29.
 * Application
 */
class KotlinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
    }
}