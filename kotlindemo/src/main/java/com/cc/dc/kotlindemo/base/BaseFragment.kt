package com.cc.dc.kotlindemo.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import de.greenrobot.event.EventBus

/**
 * Created by dc on 2018/5/31.
 * base fragment
 */
abstract class BaseFragment : Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(getLayoutId(), container, false)
        ButterKnife.bind(this, rootView!!)
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this)
        }
        return rootView
    }

    abstract fun getLayoutId(): Int

    protected open fun isRegisterEventBus() : Boolean {
        return false
    }

    override fun onDestroyView() {
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroyView()
    }
}