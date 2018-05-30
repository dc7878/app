package com.cc.dc.kotlindemo.adapter.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.bean.news.NewsEntity
import com.cc.dc.kotlindemo.bean.news.NewsImageEntity
import com.cc.dc.kotlindemo.bean.news.NewsVideoEntity
import com.cc.dc.kotlindemo.common.CommonViewHolder
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate

/**
 * Created by dc on 2018/5/30.
 * news video adapter
 */
class NewsVideoAdapter : AbsListItemAdapterDelegate<NewsVideoEntity, NewsEntity, CommonViewHolder> {

    private var context: Context? = null

    private var inflater: LayoutInflater? = null

    constructor(context: Context) : super() {
        this.context = context
        this.inflater = LayoutInflater.from(this.context)
    }

    override fun onBindViewHolder(item: NewsVideoEntity, viewHolder: CommonViewHolder, payloads: MutableList<Any>) {
    }

    override fun onCreateViewHolder(parent: ViewGroup): CommonViewHolder {
        return CommonViewHolder(inflater!!.inflate(R.layout.item_news_video, parent, false))
    }

    override fun isForViewType(item: NewsEntity, items: MutableList<NewsEntity>, position: Int): Boolean {
        return  item is NewsVideoEntity
    }
}