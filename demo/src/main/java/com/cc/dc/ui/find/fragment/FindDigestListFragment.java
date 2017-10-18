package com.cc.dc.ui.find.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.cc.dc.bean.find.FindDigestBean;
import com.cc.dc.common.custom.LinearItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.dc.R;
import com.cc.dc.ui.find.adapter.FindDigestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/10/18.
 */
public class FindDigestListFragment extends BaseFragment {

    @Bind(R.id.recycler_view_find_digest)
    LoadMoreRecyclerView recyclerView;

    private List<Object> data = new ArrayList<>();
    private FindDigestAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_digest;
    }

    @Override
    public void initView() {
        adapter = new FindDigestAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new LinearItemDecoration(getActivity(), R.drawable.item_decoration_home, LinearItemDecoration.VERTICAL));
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void lazyLoadData() {
        for (int i = 0; i < 50; i++) {
            FindDigestBean bean = new FindDigestBean();
            bean.setNickName("name>>>" + i);
            data.add(bean);
        }
        adapter.notifyDataSetChanged();
    }
}
