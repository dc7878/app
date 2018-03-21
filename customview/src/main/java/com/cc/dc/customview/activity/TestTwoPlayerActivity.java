package com.cc.dc.customview.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.customview.R;
import com.cc.dc.customview.adapter.TestPlayerAdapter;
import com.cc.dc.customview.bean.TestPlayerBean;
import com.cc.dc.customview.delegate.adapter.NewsAdapter;
import com.cc.dc.customview.delegate.adapter.NewsVideoAdapter;
import com.cc.dc.customview.delegate.bean.NewsEntity;
import com.cc.dc.customview.delegate.bean.NewsNormalEntity;
import com.cc.dc.customview.delegate.bean.NewsVideoEntity;
import com.cc.dc.customview.dialog.TestDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;

/**
 * Created by dc on 2017/12/20.
 */

public class TestTwoPlayerActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<NewsEntity> data;
    private NewsAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_player;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        data = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            NewsNormalEntity normalEntity = new NewsNormalEntity();
            data.add(normalEntity);
        }

        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            int type = random.nextInt(2);
            if (type == 0) {
                NewsNormalEntity normalEntity = new NewsNormalEntity();
                data.add(normalEntity);
            } else {
                NewsVideoEntity videoEntity = new NewsVideoEntity();
                videoEntity.type = random.nextInt(2);
                data.add(videoEntity);
            }
        }

        adapter = new NewsAdapter(this, data);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
