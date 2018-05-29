package com.cc.dc.kotlindemo.activity

import android.util.Log
import android.view.View
import android.widget.Toast
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.adapter.MainAdapter
import com.cc.dc.kotlindemo.bean.BaseBean
import com.cc.dc.kotlindemo.bean.ImageBean
import com.cc.dc.kotlindemo.bean.VideoBean
import kotlinx.android.synthetic.main.activity_main.*

/**
 * test main activity
 * 调查点点滴滴等等 /**
 *                   * 嵌套注释
 *                   */
 */
class MainActivity : BaseActivity() {

    private var list: ArrayList<BaseBean>? = null
    private var adapter: MainAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        tvName.text = "111 " + sum(1, 20)
        button.setBackgroundColor(resources.getColor(R.color.colorAccent))
    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

    private fun init() {
        list = ArrayList()
        var index = 0
        for (index in 0..10) {
            var bean = BaseBean()
            bean.age = index
            bean.name = "name is " + index
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
        val a = 1
        var s1 = "this is $a"
        Toast.makeText(this, s1, Toast.LENGTH_LONG).show()
    }

    private fun nullTest() {
        var a: String? = "1"
        a = null
    }
}
