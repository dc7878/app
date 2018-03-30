package com.cc.dc.customview.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
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
import com.cc.dc.customview.marquee.ComplexItemEntity;
import com.cc.dc.customview.marquee.ComplexViewMF;
import com.gongwen.marqueen.MarqueeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;

/**
 * Created by dc on 2017/12/20.
 */

public class TestTwoPlayerActivity extends BaseActivity {

    @Bind(R.id.marqueeView)
    MarqueeView marqueeView;

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
        List<ComplexItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ComplexItemEntity entity = new ComplexItemEntity();
            entity.time = "14:0" + i;
            entity.title = "十二是的分开刻的方式 " + i;
            list.add(entity);
        }
        ComplexViewMF complexViewMF = new ComplexViewMF(this);
        complexViewMF.setData(list);
        marqueeView.setMarqueeFactory(complexViewMF);
        marqueeView.startFlipping();


        data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            NewsNormalEntity normalEntity = new NewsNormalEntity();
            data.add(normalEntity);
        }


        adapter = new NewsAdapter(this, data);
        recyclerView.getItemAnimator().setChangeDuration(0);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
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
                adapter.notifyItemRangeChanged(2, data.size());
//                adapter.notifyDataSetChanged();
            }
        }, 2000);
    }
}
