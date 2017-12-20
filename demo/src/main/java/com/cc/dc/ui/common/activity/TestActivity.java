package com.cc.dc.ui.common.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cc.dc.R;
import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.test.TestAdapter;
import com.cc.dc.test.TestBean;
import com.cc.dc.ui.dialog.TestDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;

/**
 * Created by dc on 2017/11/2.
 */

public class TestActivity extends BaseActivity implements TestAdapter.OnItemClickListener {

    @Bind(R.id.content)
    LinearLayout content;

    @Bind(R.id.recycler_test)
    RecyclerView recyclerView;

    private List<TestBean> data;
    private TestAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_a_test;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        data = new ArrayList<>();
        adapter = new TestAdapter(this, data);
        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = data.get(position).getType();
                if (type == TestBean.TYPE.TYPE_TITLE) {
                    return 6;
                } else if (type == TestBean.TYPE.TYPE_MORE) {
                    return 6;
                } else if (type == TestBean.TYPE.TYPE_IMAGE) {
                    return 3;
                }
                return 0;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        loadData();
    }

    private void loadData() {
        Random random = new Random();
        for (int k = 0; k < 50; k++) {
            TestBean testBean1 = new TestBean();
            testBean1.setType(TestBean.TYPE.TYPE_TITLE);
            testBean1.setTitle("测试>>>" + k);
            data.add(testBean1);

            int len = random.nextInt(4) + 2;
            for (int i = 0; i < len; i++) {
                TestBean testBean = new TestBean();
                testBean.setType(TestBean.TYPE.TYPE_IMAGE);
                testBean.setImageId(R.drawable.icon);
                testBean.setImageUrl("http://www.learn2sleep.com/icon.jpg");
                data.add(testBean);
            }
            if (len >= 4) {
                TestBean testBean2 = new TestBean();
                testBean2.setType(TestBean.TYPE.TYPE_MORE);
                data.add(testBean2);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "" + position, Toast.LENGTH_LONG).show();
        Log.e("MyListener", "MyListener onItemClick");
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.removeView(view);

//        content.addView(view, 0);

        TestDialog dialog = new TestDialog(this, R.style.TestDialog, view);
//        dialog.addView(view);
        dialog.show();
    }
}
