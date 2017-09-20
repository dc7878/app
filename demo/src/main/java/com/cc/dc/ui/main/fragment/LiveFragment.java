package com.cc.dc.ui.main.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.custom.GridItemDecoration;
import com.cc.dc.dc.R;
import com.cc.dc.ui.main.adapter.LiveRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/19.
 */
public class LiveFragment extends BaseFragment {

    @Bind(R.id.recycler_view_live)
    RecyclerView recyclerView;

    private List<LiveBean> data;
    private LiveRecyclerViewAdapter adapter;
    private GridItemDecoration itemDecoration;

    private int spanCount = 3;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        adapter = new LiveRecyclerViewAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        itemDecoration = new GridItemDecoration.Builder()
                .eachEqual(20)
                .spanCount(spanCount)
                .data(data)
                .build();
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void lazyLoadData() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            LiveBean bean = new LiveBean();
            bean.setName("name info " + i);
            bean.setHeight(random.nextInt(300) + 100);
            data.add(bean);
        }
        adapter.notifyDataSetChanged();
        itemDecoration.notifyDataSetChanged();
    }
}
