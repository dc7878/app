package com.cc.dc.kotlindemo.activity

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.cc.dc.kotlindemo.R
import com.cc.dc.kotlindemo.adapter.spinner.SpinnerAdapter
import com.cc.dc.kotlindemo.base.BaseActivity
import com.cc.dc.kotlindemo.bean.SpinnerBean
import kotlinx.android.synthetic.main.activity_spinner.*

/**
 * Created by dc on 2018/6/14.
 * Some Type Spinner
 */
class SpinnerActivity : BaseActivity() {

    override fun getLayout(): Int = R.layout.activity_spinner

    override fun initView() {
        // init data
        val list = ArrayList<SpinnerBean>()
        for (index in 1..10) {
            val bean = SpinnerBean()
            bean.name = "test age $index"
            bean.age = index
            list.add(bean)
        }

        val adapter = SpinnerAdapter(this, list)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                Toast.makeText(this@SpinnerActivity, "当前选中的是 >>>" + list[position].name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}