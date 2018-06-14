package com.cc.dc.kotlindemo.activity

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.adapter.CommonAdapter
import com.cc.dc.kotlindemo.base.BaseActivity
import com.cc.dc.kotlindemo.bean.BaseBean
import kotlinx.android.synthetic.main.activity_common_list.*

/**
 * Created by dc on 2018/6/14.
 * a simple list activity
 */
class CommonListActivity : BaseActivity() {

    private val list = ArrayList<BaseBean>()

    private var adapter: CommonAdapter? = null

    override fun getLayout(): Int = R.layout.activity_common_list

    override fun initView() {
        // initData
        for (index in 1..10) {
            val bean = BaseBean()
            bean.name = "name $index"
            bean.age = index * 10
            list.add(bean)
        }
        adapter = CommonAdapter(this, list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}