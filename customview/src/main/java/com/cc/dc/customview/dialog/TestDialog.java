package com.cc.dc.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cc.dc.customview.R;
import com.cc.dc.customview.activity.TestPlayerActivity;


/**
 * Created by dc on 2017/12/20.
 */

public class TestDialog extends Dialog {

    LinearLayout container;

    private Context context;

    private View viewVideo;

    private TestPlayerActivity.DialogDismissListener listener;

    public TestDialog(@NonNull Context context, int themeResId, View viewVideo, TestPlayerActivity.DialogDismissListener listener) {
        super(context, themeResId);
        this.context = context;
        this.viewVideo = viewVideo;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_test, null);
        setContentView(view);
        container = view.findViewById(R.id.container);
        container.addView(viewVideo, 1);
    }

    @Override
    public void dismiss() {
        container.removeView(viewVideo);
        listener.dialogDismiss(viewVideo);
        super.dismiss();
    }
}
