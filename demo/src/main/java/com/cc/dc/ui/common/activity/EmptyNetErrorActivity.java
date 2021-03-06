package com.cc.dc.ui.common.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.cc.dc.R;
import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.util.view.ViewStatusHelper;

import butterknife.Bind;

/**
 * Created by dc on 2017/12/12.
 */

public class EmptyNetErrorActivity extends BaseActivity {

    @Bind(R.id.tv_name)
    TextView tvName;

    @Bind(R.id.tv_name2)
    TextView tvName2;

    private ViewStatusHelper nameHelper;
    private ViewStatusHelper nameHelper2;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    nameHelper.showEmpty();
                    break;
                case 1:
                    nameHelper.showNetError();
                    break;
                case 2:
                    nameHelper.showNormal();
                    break;
            }
        }
    };

    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    nameHelper2.showEmpty();
                    break;
                case 1:
                    nameHelper2.showNetError();
                    break;
                case 2:
                    nameHelper2.showNormal();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_empty_error;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        nameHelper = new ViewStatusHelper(this, tvName);
        nameHelper2 = new ViewStatusHelper(this, tvName2);

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(2);
            }
        }.start();



        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler2.sendEmptyMessage(0);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler2.sendEmptyMessage(1);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler2.sendEmptyMessage(2);
            }
        }.start();
    }
}
