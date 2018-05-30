package com.cc.dc.kotlindemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.bean.BaseBean
import kotlinx.android.synthetic.main.layout_item.view.*

/**
 * Created by dc on 2018/5/29.
 * some test adapter
 */
class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private var context: Context? = null
    private var list: ArrayList<BaseBean>? = null

    private var inflater: LayoutInflater? = null

    constructor(context: Context, list: ArrayList<BaseBean>) : super() {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(this.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView!!){
            tvName?.text = list?.get(position)?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(inflater!!.inflate(R.layout.layout_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item)
}