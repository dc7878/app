package com.cc.dc.kotlindemo.adapter.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.common.CommonViewHolder
import com.hannesdorfmann.adapterdelegates3.AbsFallbackAdapterDelegate

/**
 * Created by dc on 2018/6/13.
 * Error Type Adapter
 */
class ErrorTypeAdapter<T> : AbsFallbackAdapterDelegate<List<T>> {

    private var context: Context? = null

    private var inflater: LayoutInflater? = null

    constructor(context: Context) : super() {
        this.context = context
        this.inflater = LayoutInflater.from(this.context)
    }

    override fun onBindViewHolder(items: List<T>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return CommonViewHolder(inflater!!.inflate(R.layout.item_error, parent, false))
    }
}