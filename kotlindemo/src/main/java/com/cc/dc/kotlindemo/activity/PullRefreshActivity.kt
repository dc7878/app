package com.cc.dc.kotlindemo.activity

import android.util.Log
import com.cc.dc.kotlindemo.adapter.news.NewsAdapter
import com.cc.dc.kotlindemo.base.BaseListActivity
import com.cc.dc.kotlindemo.bean.news.NewsEntity
import com.cc.dc.kotlindemo.bean.news.NewsImageEntity
import com.cc.dc.kotlindemo.bean.news.NewsNormalEntity
import com.cc.dc.kotlindemo.bean.news.NewsVideoEntity
import com.cc.dc.kotlindemo.model.TestModel
import com.cc.dc.kotlindemo.net.HttpCallBack
import io.reactivex.disposables.Disposable
import java.util.*

/**
 * Created by dc on 2018/6/1.
 * test pull refresh layout
 */
class PullRefreshActivity : BaseListActivity<NewsEntity>() {

    override fun initAdapter() {
        adapter = NewsAdapter(this, list!!)
    }

    override fun loadListData() {
        super.loadListData()
        val map = HashMap<String, String>()
        map.put("tid", "548")
        map.put("page", pageNum.toString())
        map.put("pagesize", pageSize.toString())
        TestModel.getNewsList(map, object : HttpCallBack<List<NewsEntity>> {
            override fun onStart(disposable: Disposable) {
                Log.e("TestModel", "getNewsList>>>onStart>>>")
            }

            override fun onResult(result: List<NewsEntity>?) {
                loadDataSuccess(result)
                Log.e("TestModel", "getNewsList>>>onResult>>>" + result?.size)
            }

            override fun onError(msg: String) {
                Log.e("TestModel", "getNewsList>>>onError>>>" + msg)
            }
        })
    }

    override fun addData(size: Int) {
        val random = Random()
        for (index in 0..size) {
            val type = random.nextInt(3)
            when(type) {
                0-> {
                    val normalEntity = NewsNormalEntity()
                    list!!.add(normalEntity)
                }
                1 -> {
                    val videoEntity = NewsVideoEntity()
                    list!!.add(videoEntity)
                }
                2 -> {
                    val imageEntity = NewsImageEntity()
                    list!!.add(imageEntity)
                }
            }
        }
    }
}