package com.cc.dc.kotlindemo.base

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.adapter.news.NewsAdapter
import com.cc.dc.kotlindemo.utils.ViewStatusUtil
import com.cc.dc.kotlindemo.widgets.PullRefreshLayout
import kotlinx.android.synthetic.main.activity_pull_refresh.*
import java.util.*

/**
 * Created by dc on 2018/6/1.
 */
abstract class BaseListActivity<T> : BaseActivity(), PullRefreshLayout.OnRefreshListener {

    protected var pageNum = 1

    protected var list: ArrayList<T>? = ArrayList()
    protected var adapter: NewsAdapter? = null

    private var viewStatus: ViewStatusUtil? = null
    private var firstLoading: Boolean? = true

    override fun getLayout(): Int = R.layout.activity_pull_refresh

    override fun initView() {
        viewStatus = ViewStatusUtil(this, pullRefreshLayout)
        pullRefreshLayout.setRefreshListener(this)
        initAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        loadListData()
    }

    protected fun loadDataSuccess(result: List<T>?) {
        if (!firstLoading!!) {
            if (pageNum === 1) {
                pullRefreshLayout.refreshFinished()
            } else {
                pullRefreshLayout.loadMoreFinished()
            }
        }
        if (firstLoading!!) {
            firstLoading = false
            viewStatus!!.showNormal()
        }
        if (result == null || result.isEmpty()) {
            // 数据为空
            if (pageNum == 1) {
                // 第一次、刷新数据为空
                list!!.clear()
                viewStatus!!.showEmpty()
                return
            }
        }
        if (result == null || result.isEmpty()) {
            // 第二页数据为空
            Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show()
            return
        } else if (result.size < pageSize) {
            pullRefreshLayout.setHasNoMoreData(true)
        } else {
            pullRefreshLayout.setHasNoMoreData(false)
        }
        if (pageNum == 1 && result.isNotEmpty()) {
            list!!.clear()
        }
        addData(pageSize)
        adapter!!.notifyDataSetChanged()
    }

    protected open fun loadListData() {
        if (firstLoading!!) {
            viewStatus!!.showLoading()
        }
    }

    override fun refreshFinished() {
        pageNum = 1
        loadListData()
    }

    override fun loadMoreFinished() {
        pageNum ++
        loadListData()
    }

    protected abstract fun initAdapter()

    protected abstract fun addData(size: Int)
}