package com.cc.dc.ui.find.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cc.dc.bean.find.FindFeedBean;
import com.cc.dc.bean.find.TopicMessageBean;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.custom.ParentViewPager;
import com.cc.dc.dc.R;
import com.cc.dc.ui.find.adapter.FindViewPagerAdapter;
import com.cc.dc.ui.find.contract.FindContract;
import com.cc.dc.ui.find.presenter.FindPresenter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dc on 2017/9/19.
 */
public class FindFragment extends BaseFragment<FindPresenter> implements FindContract.View {

    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;

    @Bind(R.id.civ_yb)
    CircleImageView civYuBa;
    @Bind(R.id.civ_video)
    CircleImageView civVideo;
    @Bind(R.id.civ_lrs)
    CircleImageView civLRS;
    @Bind(R.id.civ_hot)
    CircleImageView civHot;

    @Bind(R.id.layout_info)
    LinearLayout layoutInfo;
    @Bind(R.id.tv_info_1)
    TextView tvInfo1;
    @Bind(R.id.tv_info_2)
    TextView tvInfo2;
    @Bind(R.id.tv_info_3)
    TextView tvInfo3;
    @Bind(R.id.tv_info_4)
    TextView tvInfo4;
    @Bind(R.id.riv_info_1)
    RoundedImageView ivInfo1;
    @Bind(R.id.riv_info_2)
    RoundedImageView ivInfo2;
    @Bind(R.id.riv_info_3)
    RoundedImageView ivInfo3;
    @Bind(R.id.riv_info_4)
    RoundedImageView ivInfo4;

    @Bind(R.id.view_pager_find)
    ParentViewPager viewPager;

    private List<BaseFragment> fragments = new ArrayList<>();
    private FindViewPagerAdapter pagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civYuBa);
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civVideo);
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civLRS);
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(civHot);

        initPagerAdapter();
    }

    @Override
    public void initPresenter() {
        presenter = new FindPresenter();
        presenter.attachView(this);
    }

    @Override
    public void lazyLoadData() {
        presenter.loadTopicMessageList();
        presenter.loadDigestList(1);
        presenter.loadTopicList(0);
    }

    @Override
    public void showTopicMessageList(List<TopicMessageBean> list) {
        if (list.size() >= 3) {
            layoutInfo.setVisibility(View.VISIBLE);
        } else {
            return;
        }
        tvInfo1.setText(list.get(0).getName());
        tvInfo2.setText(list.get(1).getName());
        tvInfo3.setText(list.get(2).getName());
        tvInfo4.setText(getResources().getString(R.string.find_more_hot_talk));

        Glide.with(this).load(list.get(0).getAvatar()).into(ivInfo1);
        Glide.with(this).load(list.get(1).getAvatar()).into(ivInfo2);
        Glide.with(this).load(list.get(2).getAvatar()).into(ivInfo3);
    }

    @Override
    public void showDigestList(List<FindFeedBean> list) {
        LUtil.e("FindFragment", "showDigestList>>>" + list.size());
    }

    @Override
    public void showTopicList(List<FindFeedBean> list) {
        LUtil.e("FindFragment", "showTopicList>>>" + list.size());
    }

    private void initPagerAdapter() {
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        List<String> list = new ArrayList<>();
        list.add("精选");
        list.add("榜单");
        list.add("鱼塘");
        list.add("小组");

        for (int i = 0; i < 4; i++) {
            fragments.add(new FindFeedListFragment());
        }
        pagerAdapter = new FindViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments, list);
        viewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(viewPager);
    }
}
