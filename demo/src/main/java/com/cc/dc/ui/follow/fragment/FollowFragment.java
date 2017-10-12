package com.cc.dc.ui.follow.fragment;

import com.bumptech.glide.Glide;
import com.cc.dc.common.ui.BaseFragment;
import com.cc.dc.dc.R;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dc on 2017/9/19.
 */
public class FollowFragment extends BaseFragment {

    @Bind(R.id.profile_image)
    CircleImageView circleImageView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void lazyLoadData() {
        Glide.with(this).load("http://www.learn2sleep.com/icon.jpg").into(circleImageView);
    }
}
