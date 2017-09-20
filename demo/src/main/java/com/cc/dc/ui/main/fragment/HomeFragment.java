package com.cc.dc.ui.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cc.dc.bean.InfoBean;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.custom.LinearItemDecoration;
import com.cc.dc.dc.R;
import com.cc.dc.ui.main.adapter.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/9/19.
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.recycler_view_home)
    RecyclerView recyclerViewHome;

    private List<InfoBean> data = new ArrayList<>();

    private HomeRecyclerViewAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        adapter = new HomeRecyclerViewAdapter(getActivity(), data);
        recyclerViewHome.setAdapter(adapter);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewHome.addItemDecoration(new LinearItemDecoration(getActivity(), R.drawable.item_decoration_home, LinearItemDecoration.VERTICAL));
    }

    @Override
    public void lazyLoadData() {
        for (int i = 0; i < 100; i++) {
            InfoBean bean = new InfoBean();
            bean.setName("name->" + i);
            bean.setDesc("desc-->" + i * 10);
            data.add(bean);
        }
        adapter.notifyDataSetChanged();
    }
}
