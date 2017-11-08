package com.cc.dc.customview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cc.dc.customview.R;
import com.cc.dc.customview.view.CustomCircleProgress;

public class MainActivity extends AppCompatActivity {

    private CustomCircleProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_layout);
        progress = (CustomCircleProgress) findViewById(R.id.circle_progress);
    }

    public void animationTest(View view) {
        Intent intent = new Intent(this, TestAnimationActivity.class);
        startActivity(intent);
    }

    public void resetCustomCircleProgress(View view) {
        progress.reset();
    }
}
