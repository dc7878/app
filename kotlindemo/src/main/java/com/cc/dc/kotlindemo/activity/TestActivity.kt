package com.cc.dc.kotlindemo.activity

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.adapter.news.NewsAdapter
import com.cc.dc.kotlindemo.base.BaseActivity
import com.cc.dc.kotlindemo.bean.BaseBean
import com.cc.dc.kotlindemo.bean.ImageBean
import com.cc.dc.kotlindemo.bean.VideoBean
import com.cc.dc.kotlindemo.bean.news.NewsEntity
import com.cc.dc.kotlindemo.bean.news.NewsImageEntity
import com.cc.dc.kotlindemo.bean.news.NewsNormalEntity
import com.cc.dc.kotlindemo.bean.news.NewsVideoEntity
import com.cc.dc.kotlindemo.utils.KotlinStringUtil
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*

/**
 * Created by dc on 2018/5/31.
 * some test code
 */
class TestActivity : BaseActivity() {
    private var list: ArrayList<NewsEntity>? = null
    private var adapter: NewsAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        initData()

        tvName.text = "111 " + sum(1, 20)
        button.setBackgroundColor(resources.getColor(R.color.colorAccent))

        adapter = NewsAdapter(this, list!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        recyclerView.postDelayed(Runnable {
            kotlin.run {
                addData(10)
                adapter!!.notifyDataSetChanged()
            }
        }, 2000)
    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

    private fun initData() {
        list = ArrayList()
        addData(20)
    }

    private fun addData(size: Int) {
        var random = Random()
        for (index in 0..size) {
            var type = random.nextInt(3)
            when(type) {
                0-> {
                    var normalEntity = NewsNormalEntity()
                    list!!.add(normalEntity)
                }
                1 -> {
                    var videoEntity = NewsVideoEntity()
                    list!!.add(videoEntity)
                }
                2 -> {
                    var imageEntity = NewsImageEntity()
                    list!!.add(imageEntity)
                }
            }
        }
    }

    fun clickButton(view: View) {
//        Toast.makeText(this, "button click " + view.id, Toast.LENGTH_LONG).show()
        someTest()
    }

    private fun sum(a: Int, b: Int): Int{
        var  video: BaseBean = VideoBean()
        video.age = 1
        if (video is VideoBean) {
            video.video
        } else if (video is ImageBean) {
            video.images
        }

        val items = listOf(video, video)
        for (item in items) {
            Log.e("MainKotlin", "1>>>" + item.age)
        }
        for (item in items.indices) {
            if (item == 2) {
                Log.e("MainKotlin", "22>>>" + items[item].age)
            }
            Log.e("MainKotlin", "2>>>" + items[item].age)
        }

        var index = 0
        while (index < items.size) {
            Log.e("MainKotlin", "3>>>" + items[index].age)
            index++
        }
        return a + b
    }

    private fun  getTypeValue(obj: Any): Int {
        when(obj) {
            1 ->{
                val a = 10
                return 1 + a - 1
            }
            "2" ->{
                var a = 10
                var b = 20
                var c = 2330
                c += a + b
                c -= a
                a++
                return b + c - a
            }
            else ->{
                var b = 12
                b++
                return b + sum(11, b)
            }
        }
    }

    private fun someTest() {
        var realStr = KotlinStringUtil.getRealStr(12)
        val a = 1
        var s1 = "this is $a"
        Toast.makeText(this, s1, Toast.LENGTH_LONG).show()
    }

    private fun nullTest() {
        var a: String? = "1"
        a = null
    }
}