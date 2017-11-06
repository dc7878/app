package com.cc.dc.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.cc.dc.customview.R;
import com.cc.dc.customview.animation.ViewAnimation;

/**
 * Created by dc on 2017/11/6.
 */

public class TestAnimationActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        imageView = (ImageView) findViewById(R.id.image);
    }

    public void onRotaAnimation(View view) {
        ViewAnimation.rotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 300, imageView);
    }

    public void onTranslateAnimation(View view) {
        ViewAnimation.translateAnimation(0, 4.5f, 0, 4.5f, imageView);
    }

    public void onClickTarget(View view) {
        Toast.makeText(this, "111", Toast.LENGTH_LONG).show();
    }
}
