package com.cc.dc.ui.main.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cc.dc.bean.LiveBean;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.custom.GridItemDecoration;
import com.cc.dc.dc.R;
import com.cc.dc.ui.main.adapter.LiveRecyclerViewAdapter;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

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
        itemDecoration = new GridItemDecoration(spanCount, 30, data);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void lazyLoadData() {
        for (int i = 0; i < 100; i++) {
            LiveBean bean = new LiveBean();
            bean.setName("name info " + i);
            data.add(bean);
        }
        adapter.notifyDataSetChanged();
        itemDecoration.notifyDataSetChanged();
    }
}
