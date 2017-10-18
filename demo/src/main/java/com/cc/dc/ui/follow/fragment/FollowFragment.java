package com.cc.dc.ui.follow.fragment;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.cc.dc.bean.follow.FollowBean;
import com.cc.dc.bean.follow.FollowCommentBean;
import com.cc.dc.common.custom.GridItemDecoration;
import com.cc.dc.common.custom.LoadMoreRecyclerView;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.dc.R;
import com.cc.dc.ui.follow.adapter.FollowRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dc on 2017/9/19.
 */
public class FollowFragment extends BaseFragment {

    @Bind(R.id.profile_image)
    CircleImageView circleImageView;

    @Bind(R.id.lrv_view_follow)
    LoadMoreRecyclerView recyclerView;

    private List<Object> data = new ArrayList<>();
    private FollowRecyclerViewAdapter adapter;
    private GridItemDecoration itemDecoration;
    private int spanCount = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    public void initView() {
        adapter = new FollowRecyclerViewAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
        itemDecoration = new GridItemDecoration.Builder()
                .leftSpace(10)
                .rightSpace(10)
                .rowSpace(20)
                .columnSpace(20)
                .topSpace(0)
                .bottomSpace(20)
                .spanCount(spanCount)
                .data(data)
                .build();
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void lazyLoadData() {
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(circleImageView);

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            FollowBean bean = new FollowBean();
            bean.setName("name->" + i);
            bean.setDesc("desc-->" + i * 10);
            data.add(bean);
            int randomInt = random.nextInt(4);
            if (randomInt == 0) {
                int len = random.nextInt(10) + 5;
                for (int k = 0; k < len; k++) {
                    FollowCommentBean commentBean = new FollowCommentBean();
                    commentBean.setInfo("info>>>" + k * 1000);
                    data.add(commentBean);
                }
            }
        }
        adapter.notifyDataSetChanged();
        itemDecoration.notifyDataSetChanged();
    }
}
