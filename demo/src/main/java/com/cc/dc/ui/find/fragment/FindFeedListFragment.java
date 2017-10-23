package com.cc.dc.ui.find.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.cc.dc.bean.find.FindFeedBean;
import com.cc.dc.common.custom.LinearItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.dc.R;
import com.cc.dc.ui.find.adapter.FindFeedAdapter;
import com.cc.dc.ui.find.contract.FindFeedContract;
import com.cc.dc.ui.find.presenter.FindFeedPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dc on 2017/10/18.
 */
public class FindFeedListFragment extends BaseFragment<FindFeedPresenter> implements FindFeedContract.View {

    @Bind(R.id.recycler_view_find_digest)
    LoadMoreRecyclerView recyclerView;

    private List<Object> data = new ArrayList<>();
    private FindFeedAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_digest;
    }

    @Override
    public void initView() {
        adapter = new FindFeedAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new LinearItemDecoration(getActivity(), R.drawable.item_decoration_find_feed, LinearItemDecoration.VERTICAL));
    }

    @Override
    public void initPresenter() {
        presenter = new FindFeedPresenter();
        presenter.attachView(this);
    }

    @Override
    public void lazyLoadData() {
        presenter.loadFindFeedList(1);
    }

    @Override
    public void showFindFeedList(List<FindFeedBean> list) {
        LUtil.e("FindFeedListFragment", "FindFeedBean>>>" + list.size());
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
