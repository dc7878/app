package com.cc.dc.kotlindemo.adapter.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cc.dc.kotlindemo.common.CommonViewHolder

/**
 * Created by dc on 2018/6/14.
 * Base Adapter
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<CommonViewHolder>{

    private var context: Context? = null
    protected var list: ArrayList<T>? = null

    private var inflater: LayoutInflater? = null

    constructor(context: Context, list: ArrayList<T>) : super() {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(this.context)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommonViewHolder {
        return CommonViewHolder(inflater!!.inflate(getItemLayoutId(), parent, false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    abstract fun getItemLayoutId(): Int
}