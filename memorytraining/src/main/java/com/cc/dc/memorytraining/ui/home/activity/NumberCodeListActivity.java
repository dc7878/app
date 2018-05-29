package com.cc.dc.memorytraining.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cc.dc.common.custom.StaggeredItemDecoration;
import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.common.utils.ResourceUtil;
import com.cc.dc.memorytraining.R;
import com.cc.dc.memorytraining.bean.NumberCodeBean;
import com.cc.dc.memorytraining.ui.home.adapter.NumberCodeRecyclerViewAdapter;
import com.cc.dc.memorytraining.ui.user.model.SomeTestModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NumberCodeListActivity extends BaseActivity {

    @Bind(R.id.recycler_view_main)
    RecyclerView recyclerView;

    private StaggeredItemDecoration itemDecoration;
    private int spanCount = 5;
    private List<NumberCodeBean> data;
    private NumberCodeRecyclerViewAdapter adapter;

    private String[] singleNumber = {
            "太阳", "铅笔", "鸭子", "耳朵", "红旗",
            "秤钩", "哨子", "镰刀", "葫芦", "勺子"};

    private String[] doubleNumber= {
            "望远镜",
            "小树","铃儿","三角凳","四驱车","手套","左轮手枪","锄头","轮滑鞋","加菲猫","棒球",
            "筷子","婴儿","医生","钥匙","鹦鹉","杨柳","仪器","泥巴","药酒","香烟",
            "鳄鱼","双胞胎","和尚","闹钟","二胡","河流","耳机","恶霸","饿囚","森林",
            "鲨鱼","扇儿","钻石","绅士","松鼠","山路","山鸡","沙发","三角尺","司令",
            "司仪","柿儿","石山","狮子","师父","石榴","司机","石板","石球","武林高手",
            "狐狸","斧儿","火山","护士","火车","蜗牛","武器","火把","乌龟","榴莲",
            "儿童节","驴儿","流沙","牛屎","老虎","溜溜球","油漆","喇叭","太极","麒麟",
            "机翼","企鹅","鸡蛋","骑士","蜘蛛","犀牛","机器人","青蛙","气球","巴黎",
            "蚂蚁","靶儿","花生","巴士","白兔","八路","白棋","爸爸","芭蕉","精灵",
            "球衣","球儿","救生圈","教师","救护车","酒楼","香港","酒吧","胶卷",
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        data = new ArrayList<>();
        adapter = new NumberCodeRecyclerViewAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
        itemDecoration = new StaggeredItemDecoration.Builder()
                .eachEqual(20)
                .spanCount(spanCount)
                .data(data)
                .build();
        recyclerView.addItemDecoration(itemDecoration);

        loadData();
    }

    private void loadData() {

        Map<String, String> map = new HashMap<>();
        map.put("tid", "0");
        map.put("page", String.valueOf(1));
        map.put("pagesize", String.valueOf(2));
        new SomeTestModel().test(map);

        for (int i = 0; i < 100; i++) {
            NumberCodeBean bean = new NumberCodeBean();
            int resId;
            String info;
            if (i < 10) {
                resId = ResourceUtil.getResource(this, "number_code_0"+i);
                info = "0" + i;
            } else {
                resId = ResourceUtil.getResource(this, "number_code_"+i);
                info = String.valueOf(i);
            }
            bean.setDesc(doubleNumber[i]+"( "+ info +" )");
            bean.setResId(resId);
            data.add(bean);
        }
        for (int i = 0; i < 10; i++) {
            NumberCodeBean bean = new NumberCodeBean();
            int resId = ResourceUtil.getResource(this, "number_code_"+i);
            bean.setDesc(singleNumber[i]+"( "+ i +" )");
            bean.setResId(resId);
            data.add(bean);
        }

        adapter.notifyDataSetChanged();
        itemDecoration.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_test)
    protected void clickTest() {
        Intent intent = new Intent(this, NumberCodeTestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_pile_path)
    protected void clickPile() {
        Intent intent = new Intent(this, PilePathActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_pi)
    protected void  clickPI() {
        Intent intent = new Intent(this, PIMemoryActivity.class);
        startActivity(intent);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LUtil.e("NumberTest", "111111111");
                e.onNext(1111);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {

                return integer + " " + 10;
            }
        }).flatMap(new Function<String, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(String s) throws Exception {
                return Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                    }
                });
            }
        }).subscribeOn(Schedulers.io())
        .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
