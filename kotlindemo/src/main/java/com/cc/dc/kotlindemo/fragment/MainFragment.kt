package com.cc.dc.kotlindemo.fragment

import android.content.Intent
import android.util.Log
import butterknife.OnClick
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.activity.PullRefreshActivity
import com.cc.dc.kotlindemo.activity.TestActivity
import com.cc.dc.kotlindemo.base.BaseFragment
import com.cc.dc.kotlindemo.bean.ChannelBean
import com.cc.dc.kotlindemo.bean.NewsBean
import com.cc.dc.kotlindemo.event.OpenDrawerEvent
import com.cc.dc.kotlindemo.model.TestModel
import com.cc.dc.kotlindemo.net.HttpCallBack
import de.greenrobot.event.EventBus
import io.reactivex.disposables.Disposable
import java.util.HashMap

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

        TestModel.getChannelJson("1", object : HttpCallBack<List<ChannelBean>>{
            override fun onStart(disposable: Disposable) {
                Log.e("TestModel", "MainFragment>>>onStart>>>")
            }

            override fun onResult(result: List<ChannelBean>) {
                Log.e("TestModel", "MainFragment>>>onResult>>>" + result)
                for(item in result) {
                    Log.e("TestModel", "MainFragment>>>onResult>>>" + item.name + " " + item.tid)
                }
            }

            override fun onError(msg: String) {
                Log.e("TestModel", "MainFragment>>>onError>>>" + msg)
            }
        })
    }

    @OnClick(R.id.btn_test_activity)
    fun goToTestActivity() {
        val intent = Intent(context, TestActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.btn_refresh_activity)
    fun goToRefreshLayout() {
        val intent = Intent(context, PullRefreshActivity::class.java)
        startActivity(intent)
    }
}