package com.cc.dc.kotlindemo.adapter.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.bean.news.NewsEntity
import com.cc.dc.kotlindemo.bean.news.NewsNormalEntity
import com.cc.dc.kotlindemo.common.CommonViewHolder
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.layout_item.view.*

/**
 * Created by dc on 2018/5/30.
 * news normal adapter
 */
class NewsNormalAdapter : AbsListItemAdapterDelegate<NewsNormalEntity, NewsEntity, CommonViewHolder> {

    private var context: Context? = null

    private var inflater: LayoutInflater? = null

    constructor(context: Context) : super() {
        this.context = context
        this.inflater = LayoutInflater.from(this.context)
    }

    override fun onBindViewHolder(item: NewsNormalEntity, viewHolder: CommonViewHolder, payloads: MutableList<Any>) {
        with(viewHolder.itemView!!) {
            tvName?.text = "222"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): CommonViewHolder {
        return CommonViewHolder(inflater!!.inflate(R.layout.item_news_normal, parent, false))
    }

    override fun isForViewType(item: NewsEntity, items: MutableList<NewsEntity>, position: Int): Boolean {
        return  item is NewsNormalEntity
    }
}