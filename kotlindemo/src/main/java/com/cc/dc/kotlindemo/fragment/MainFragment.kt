package com.cc.dc.kotlindemo.fragment

import android.util.Log
import butterknife.OnClick
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.base.BaseFragment
import com.cc.dc.kotlindemo.event.OpenDrawerEvent
import com.cc.dc.kotlindemo.model.TestModel
import com.cc.dc.kotlindemo.net.HttpCallBack
import de.greenrobot.event.EventBus
import io.reactivex.disposables.Disposable

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

        TestModel.getChannelJson("1", object : HttpCallBack<String>{
            override fun onStart(disposable: Disposable) {
                Log.e("TestModel", "MainFragment>>>onStart>>>")
            }

            override fun onResult(result: String) {
                Log.e("TestModel", "MainFragment>>>onResult>>>" + result)
            }

            override fun onError(msg: String) {
                Log.e("TestModel", "MainFragment>>>onError>>>" + msg)
            }
        })
    }
}