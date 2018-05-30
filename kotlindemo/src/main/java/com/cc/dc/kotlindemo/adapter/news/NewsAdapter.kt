package com.cc.dc.kotlindemo.adapter.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cc.dc.kotlindemo.bean.news.NewsEntity
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager

/**
 * Created by dc on 2018/5/30.
 * news adapter
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var context: Context? = null
    private var delegatesManager: AdapterDelegatesManager<List<NewsEntity>>? = null
    private var list: List<NewsEntity>? = null

    constructor(context: Context, list: List<NewsEntity>) : super() {
        this.context = context
        this.list = list

        this.delegatesManager = AdapterDelegatesManager()
        this.delegatesManager!!.addDelegate(NewsNormalAdapter(context))
        this.delegatesManager!!.addDelegate(NewsImageAdapter(context))
        this.delegatesManager!!.addDelegate(NewsVideoAdapter(context))
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager!!.getItemViewType(list!!, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager!!.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager!!.onBindViewHolder(list!!, position, holder)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}