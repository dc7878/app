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
import kotlinx.android.synthetic.main.activity_pull_refresh.*
import java.util.*

/**
 * Created by dc on 2018/6/1.
 * test pull refresh layout
 */
class PullRefreshActivity : BaseListActivity<NewsEntity>() {

    override fun initAdapter() {
        adapter = NewsAdapter(this, list)
    }

    override fun needHandlerDataSelf(): Boolean {
        return true
    }

    override fun loadListData() {
        super.loadListData()
        val map = HashMap<String, String>()
        map.put("tid", "548")
        map.put("page", pageNum.toString())
        map.put("pagesize", pageSize.toString())
        recyclerView.postDelayed(Runnable {
            kotlin.run {
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
        },  5000)
    }

    override fun needHandlerData(result: List<NewsEntity>?) : List<NewsEntity>? {
        super.needHandlerData(result)
        val random = Random()
        val list = mutableListOf<NewsEntity>()
        // add a error type item data
        list.add(NewsEntity())
        for (item in result!!) {
            val type = random.nextInt(3)
            when(type) {
                0-> {
                    val normalEntity = NewsNormalEntity()
                    normalEntity.title = item.title
                    list.add(normalEntity)
                }
                1 -> {
                    val videoEntity = NewsVideoEntity()
                    videoEntity.title = item.title
                    list.add(videoEntity)
                }
                2 -> {
                    val imageEntity = NewsImageEntity()
                    imageEntity.title = item.title
                    list.add(imageEntity)
                }
            }
        }
        return list
    }
}