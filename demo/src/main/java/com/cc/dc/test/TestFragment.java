package com.cc.dc.test;

import android.app.admin.DeviceAdminInfo;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cc.dc.R;
import com.cc.dc.common.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;

/**
 * Created by dc on 2017/11/2.
 */
public class TestFragment extends BaseFragment {

    @Bind(R.id.recycler_test)
    RecyclerView recyclerView;

    private List<TestBean> data;
    private TestAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_a_test;
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        adapter = new TestAdapter(getActivity(), data);

        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = data.get(position).getType();
                switch (type) {
                    case TestBean.TYPE.TYPE_TITLE :
                    case TestBean.TYPE.TYPE_MORE:
                        return 6;
                    case TestBean.TYPE.TYPE_IMAGE:
                        return 3;
                }
                return 0;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void lazyLoadData() {
        Random random = new Random();
        // TODO: 2017/11/2 数据填充
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
                data.add(testBean);
            }
            if (len == 4) {
                TestBean testBean2 = new TestBean();
                testBean2.setType(TestBean.TYPE.TYPE_MORE);
                data.add(testBean2);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
