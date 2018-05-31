package com.cc.dc.kotlindemo.activity

import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.base.BaseActivity
import com.cc.dc.kotlindemo.event.OpenDrawerEvent
import com.cc.dc.kotlindemo.fragment.DrawerLeftFragment
import com.cc.dc.kotlindemo.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * test main activity
 * 调查点点滴滴等等 /**
 *                   * 嵌套注释
 *                   */
 */
class MainActivity : BaseActivity() {

    override fun getLayout(): Int = R.layout.activity_main

    override fun isRegisterEventBus(): Boolean {
        return true
    }

    override fun initView() {
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content, MainFragment())
        fragmentTransaction.add(R.id.leftDrawer, DrawerLeftFragment())
        fragmentTransaction.commit()
    }

    @SuppressWarnings("unused")
    public fun onEvent(event: OpenDrawerEvent?) {
        drawerLayout.openDrawer(leftDrawer, true)
    }
}
