package com.cc.dc.kotlindemo.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import de.greenrobot.event.EventBus


/**
 * Created by dc on 2018/5/29.
 * BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val pageSize = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this)
        }
        ButterKnife.bind(this)
        initView()
    }

    protected abstract fun getLayout() : Int

    protected open abstract fun initView()

    protected open fun isRegisterEventBus() : Boolean {
        return false
    }

    override fun onDestroy() {
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }
}