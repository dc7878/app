package com.cc.dc.customview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cc.dc.customview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_layout);
    }

    public void animationTest(View view) {
        Intent intent = new Intent(this, TestAnimationActivity.class);
        startActivity(intent);
    }
}
