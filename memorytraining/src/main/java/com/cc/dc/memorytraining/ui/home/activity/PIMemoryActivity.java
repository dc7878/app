package com.cc.dc.memorytraining.ui.home.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.memorytraining.R;

import butterknife.Bind;

/**
 * Created by dc on 2017/12/29.
 * PI 1000 位
 */

public class PIMemoryActivity extends BaseActivity {

    @Bind(R.id.pi_content)
    TextView tvPI;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pi_memory;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tvPI.setText(stringCal(getResources().getString(R.string.pi_1000)));
    }

    private String stringCal(String info) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < info.length(); i++) {
            builder.append(info.substring(i, i + 1));
            if (i > 0 && i % 2 != 0) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
