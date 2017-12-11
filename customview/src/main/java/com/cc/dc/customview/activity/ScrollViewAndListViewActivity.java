package com.cc.dc.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.cc.dc.customview.R;
import com.cc.dc.customview.adapter.ScrollAdapter;
import com.cc.dc.customview.bean.ScrollBean;
import com.cc.dc.customview.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dc on 2017/12/11.
 */

public class ScrollViewAndListViewActivity extends Activity {

    private ScrollView scrollView;

    private ListView listView;

    private List<ScrollBean> data;
    private ScrollAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);
        initViews();
        initData();
    }

    private void initViews() {
        scrollView = findViewById(R.id.scrollView);
        listView = findViewById(R.id.list_view);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = ScreenUtil.getScreenHeight(this) - ScreenUtil.getStatusHeight(this);
        listView.setLayoutParams(params);

        Log.e("ScrollViewAnd", "" + ScreenUtil.getScreenHeight(this) + ">>>" + ScreenUtil.getStatusHeight(this));
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ScrollBean bean = new ScrollBean();
            bean.setName(i * 10 + "---info");
            data.add(bean);
        }
        adapter = new ScrollAdapter(data, this);
        listView.setAdapter(adapter);
    }
}
