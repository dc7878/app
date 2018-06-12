package com.cc.dc.kotlindemo.adapter.news

import android.content.Context
import com.cc.dc.kotlindemo.adapter.base.BaseMultiTypeAdapter
import com.cc.dc.kotlindemo.bean.news.NewsEntity
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager

/**
 * Created by dc on 2018/5/30.
 * news adapter
 */
class NewsAdapter(context: Context, list: List<NewsEntity>) : BaseMultiTypeAdapter<NewsEntity>(context, list) {

    override fun initDelegateAdapter(delegatesManager: AdapterDelegatesManager<List<NewsEntity>>?) {
        delegatesManager!!.addDelegate(NewsNormalAdapter(context!!))
        delegatesManager!!.addDelegate(NewsImageAdapter(context!!))
        delegatesManager!!.addDelegate(NewsVideoAdapter(context!!))
    }
}