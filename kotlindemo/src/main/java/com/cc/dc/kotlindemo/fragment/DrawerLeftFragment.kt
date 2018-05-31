package com.cc.dc.kotlindemo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cc.dc.kotlindemo.R

/**
 * Created by dc on 2018/5/31.
 */
class DrawerLeftFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_drawer_left, container, false)
    }
}