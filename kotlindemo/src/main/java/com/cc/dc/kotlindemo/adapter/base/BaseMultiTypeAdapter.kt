package com.cc.dc.kotlindemo.adapter.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager

/**
 * Created by dc on 2018/6/12.
 * Base Multi Type Adapter
 */
abstract class BaseMultiTypeAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var context: Context? = null
    private var delegatesManager: AdapterDelegatesManager<List<T>>
    private var list: List<T>

    constructor(context: Context, list: List<T>) : super() {
        this.context = context
        this.list = list

        this.delegatesManager = AdapterDelegatesManager()
        initDelegateAdapter(delegatesManager)
        delegatesManager.fallbackDelegate = BaseFallDelegateAdapter(context)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(list, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(list, position, holder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    abstract fun initDelegateAdapter(delegatesManager: AdapterDelegatesManager<List<T>>?)
}