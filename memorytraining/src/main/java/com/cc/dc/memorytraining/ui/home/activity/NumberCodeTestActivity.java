package com.cc.dc.memorytraining.ui.home.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.memorytraining.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by dc on 2017/10/10.
 */
public class NumberCodeTestActivity extends BaseActivity {

    @Bind(R.id.tv_code)
    TextView tvCode;

    private int count = 20;
    private List<String> list;
    private List<String> currentList;
    private List<String> tempList;
    private Random random;

    @Override
    public int getLayoutId() {
        return R.layout.activity_number_code_test;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        loadData();
        currentList = new ArrayList<>();
        tempList = new ArrayList<>();
        random = new Random();
    }

    private void loadData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String info;
            if (i < 10) {
                info = "0" + i;
            } else {
                info = String.valueOf(i);
            }
            list.add(info);
        }
    }

    @OnClick(R.id.btn_create)
    protected void clickCreate() {
        createCurrentList();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < currentList.size(); i++) {
            buffer.append(currentList.get(i));
            buffer.append(" ");
        }
        String info = buffer.toString();
        info = info.substring(0, info.length() - 1);
        tvCode.setText(info);
    }

    private void createCurrentList() {
        tempList.clear();
        currentList.clear();
        tempList.addAll(list);
        count = 20;
        while (count > 0) {
            int position = random.nextInt(tempList.size());
            currentList.add(tempList.get(position));
            tempList.remove(position);
            count--;
        }
    }
}
