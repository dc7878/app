package com.cc.dc.kotlindemo.adapter.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.bean.SpinnerBean

/**
 * Created by dc on 2018/6/14.
 * A simple spinner adapter
 */
class SpinnerAdapter : BaseAdapter {

    private var context: Context? = null

    private var list: ArrayList<SpinnerBean>? = null

    private var layoutInflater: LayoutInflater? = null

    constructor(context: Context?, list: ArrayList<SpinnerBean>?) : super() {
        this.context = context
        this.list = list
        this.layoutInflater = LayoutInflater.from(this.context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = layoutInflater!!.inflate(R.layout.item_spinner_normal, null)

        return convertView!!
    }

    override fun getItem(position: Int): Any {
        return list!![position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list!!.size
}