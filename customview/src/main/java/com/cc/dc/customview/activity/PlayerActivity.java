package com.cc.dc.customview.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.customview.R;
import com.cc.dc.customview.bean.TestPlayerBean;
import com.cc.dc.customview.recyclerviewadapter.BaseRecyclerViewAdapter;
import com.cc.dc.customview.recyclerviewadapter.PlayerDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/12/27.
 */

public class PlayerActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<TestPlayerBean> data;

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
        for (int i = 0; i < 40; i++) {
            TestPlayerBean bean = new TestPlayerBean();
            if (i == 0) {
                bean.setType(1);
                bean.setUrl("http://test86400.b0.upaiyun.com/7937144.mp4");
            }
            bean.setTitle("Player->" + i);
            data.add(bean);
        }

        BaseRecyclerViewAdapter<TestPlayerBean, PlayerDelegate.PlayerViewHolder> adapter = new BaseRecyclerViewAdapter(this, data);
        adapter.addAdapterDelagate(new PlayerDelegate());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
