package com.cc.dc.memorytraining.ui.home.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.memorytraining.R;

import butterknife.Bind;

/**
 * Created by dc on 2017/10/11.
 * 桩点路径页面
 */
public class PilePathActivity extends BaseActivity {

    @Bind(R.id.tv_path1)
    TextView tvPath1;
    @Bind(R.id.tv_path2)
    TextView tvPath2;
    @Bind(R.id.tv_path3)
    TextView tvPath3;

    private final String path1 = "热水壶--洗衣机--体重秤--健步机--储衣箱--枕头--桌子--显示器--衣橱--椅子";

    private final String path2 = "门--窗户--麻将桌--卧室--音响--电视机--阳台--沙发--厨房--储物间";

    private final String path3 = "厕所--饭桌--窗帘--书柜--双人床--镜子--花盆--抽屉--空调--冰箱";

    @Override
    public int getLayoutId() {
        return R.layout.activity_pile_path;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tvPath1.setText(path1);
        tvPath2.setText(path2);
        tvPath3.setText(path3);
    }
}
