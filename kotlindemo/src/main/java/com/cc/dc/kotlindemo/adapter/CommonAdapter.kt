package com.cc.dc.kotlindemo.adapter

import android.content.Context
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.adapter.base.BaseAdapter
import com.cc.dc.kotlindemo.bean.BaseBean
import com.cc.dc.kotlindemo.common.CommonViewHolder
import kotlinx.android.synthetic.main.item_common.view.*

/**
 * Created by dc on 2018/5/29.
 * Common adapter
 */
class CommonAdapter(context: Context, list: ArrayList<BaseBean>) : BaseAdapter<BaseBean>(context, list) {

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        with(holder.itemView!!){
            tvName?.text = list?.get(position)?.name
        }
    }

    override fun getItemLayoutId(): Int = R.layout.item_common
}