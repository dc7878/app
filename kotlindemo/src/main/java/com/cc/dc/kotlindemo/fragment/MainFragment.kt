package com.cc.dc.kotlindemo.fragment

import android.content.Intent
import android.util.Log
import butterknife.OnClick
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.activity.CommonListActivity
import com.cc.dc.kotlindemo.activity.PullRefreshActivity
import com.cc.dc.kotlindemo.activity.SpinnerActivity
import com.cc.dc.kotlindemo.activity.TestActivity
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

        val accessKey = "9716504e-efea-420e-9571-c62f6183df36"
        val secretKey = "33c6af200c39475c8f9ce7a4ac3f7e37"

        Log.e("MainFragment", "showDrawerLeft>>>")
        EventBus.getDefault().post(OpenDrawerEvent())

//        TestModel.getChannelJson("1", object : HttpCallBack<List<ChannelBean>>{
//            override fun onStart(disposable: Disposable) {
//                Log.e("TestModel", "MainFragment>>>onStart>>>")
//            }
//
//            override fun onResult(result: List<ChannelBean>?) {
//                Log.e("TestModel", "MainFragment>>>onResult>>>" + result)
//            }
//
//            override fun onError(msg: String) {
//                Log.e("TestModel", "MainFragment>>>onError>>>" + msg)
//            }
//        })
    }

    @OnClick(R.id.btn_test_activity)
    fun goToTestActivity() {
        val intent = Intent(context, TestActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.btn_common_list_activity)
    fun goToCommonListActivity() {
        val intent = Intent(context, CommonListActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.btn_refresh_activity)
    fun goToRefreshLayout() {
        val intent = Intent(context, PullRefreshActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.btn_spinner_activity)
    fun goToSpinnerActivity() {
        val intent = Intent(context, SpinnerActivity::class.java)
        startActivity(intent)
    }
}