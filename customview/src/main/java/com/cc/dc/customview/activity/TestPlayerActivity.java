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
import com.cc.dc.customview.dialog.TestDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/12/20.
 */

public class TestPlayerActivity extends BaseActivity implements TestPlayerAdapter.OnItemClickListener {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<TestPlayerBean> data;
    private TestPlayerAdapter adapter;

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

        adapter = new TestPlayerAdapter(this, data);
        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClickListener(final View view, int position) {
        final ViewGroup viewGroup = (ViewGroup) view.getParent();
        final View holder = viewGroup.findViewById(R.id.tv_title);

        viewGroup.removeView(view);
        holder.setVisibility(View.VISIBLE);

        TestDialog dialog = new TestDialog(this, R.style.TestDialog, view, new DialogDismissListener() {
            @Override
            public void dialogDismiss(View viewInfo) {
                viewGroup.addView(view);
                holder.setVisibility(View.GONE);
            }
        });
        dialog.show();
    }

    public interface DialogDismissListener {
        void dialogDismiss(View view);
    }
}
