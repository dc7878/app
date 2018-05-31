package com.cc.dc.kotlindemo.fragment

import android.util.Log
import butterknife.OnClick
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.base.BaseFragment
import com.cc.dc.kotlindemo.event.OpenDrawerEvent
import de.greenrobot.event.EventBus

/**
 * Created by dc on 2018/5/31.
 * main fragment
 */
class MainFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_main

    @OnClick(R.id.btn_show_drawer)
    fun showDrawerLeft() {
        Log.e("MainFragment", "showDrawerLeft>>>")
        EventBus.getDefault().post(OpenDrawerEvent())
    }
}