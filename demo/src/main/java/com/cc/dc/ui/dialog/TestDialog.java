package com.cc.dc.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cc.dc.R;


/**
 * Created by dc on 2017/12/20.
 */

public class TestDialog extends Dialog {

    LinearLayout container;

    private Context context;

    private View viewVideo;

    public TestDialog(@NonNull Context context, int themeResId, View viewVideo) {
        super(context, themeResId);
        this.context = context;
        this.viewVideo = viewVideo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_test, null);
        setContentView(view);
        container = view.findViewById(R.id.container);
        container.addView(viewVideo, 1);
        Log.e("TestDialog", "TestDialog>>>onCreate");
    }

    public void addView(View view) {
        Log.e("TestDialog", "TestDialog>>>addView");
        // container.addView(view, container.getChildCount());
    }
}
