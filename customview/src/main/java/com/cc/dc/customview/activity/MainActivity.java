package com.cc.dc.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cc.dc.customview.R;
import com.cc.dc.customview.view.CustomCircleProgress;

public class MainActivity extends Activity {

    private CustomCircleProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_layout);
        progress = findViewById(R.id.circle_progress);
    }

    public void animationTest(View view) {
        Intent intent = new Intent(this, TestAnimationActivity.class);
        startActivity(intent);
    }

    public void resetCustomCircleProgress(View view) {
        progress.reset();
    }

    public void asyncTaskTest(View view) {
        Intent intent = new Intent(this, TestAsyncTaskActivity.class);
        startActivity(intent);


        Intent intentService = new Intent(this, TestService.class);
        startService(intentService);
    }

    @Override
    protected void onPause() {
        Log.e("MainActivity", "onPause");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("MainActivity", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.e("MainActivity", "onStop");
        super.onStop();
    }
}
