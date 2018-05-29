package com.cc.dc.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by dc on 2018/5/29.
 * BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initView()
    }

    protected abstract fun getLayout() : Int

    protected abstract fun initView()

    protected abstract fun isRegisterEventBus() : Boolean
}