package com.cc.dc.customview.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.cc.dc.customview.R;

/**
 * Created by dc on 2017/11/6.
 */

public class CustomVolumeControlBar extends View {

    private Bitmap bitmap;

    private Paint paint;

    private int bitmapSize = 200;

    private int radius = 250;

    private int size = 100;

    private int roundHeight = 30;
    private int roundWidth = 60;
    private int  rx = 20;

    private int centerX;
    private int centerY;

    private int count = 10;

    private int progress = 10;

    private ObjectAnimator animator;

    public CustomVolumeControlBar(Context context) {
        this(context, null);
    }

    public CustomVolumeControlBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVolumeControlBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        bitmap = Bitmap.createScaledBitmap(bitmap, bitmapSize, bitmapSize, false);

        size += radius * 2;

        centerX = size / 2;
        centerY = size / 2;

        animator = ObjectAnimator.ofInt(this, "progress", 0, 100);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.cancel();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        canvas.drawBitmap(bitmap, centerX - bitmapSize / 2, centerY - bitmapSize / 2, null);


        paint.setColor(Color.RED);
        for (int i = 0; i < count; i++) {
            canvas.save();
            int left = centerX - roundWidth / 2;
            int top = centerY - radius - roundHeight + 50;
            int right = left + roundWidth;
            int bottom = top + roundHeight;
            canvas.rotate(360 / count * i, centerX, centerY);
            canvas.drawRoundRect(left, top, right, bottom, rx, rx, paint);
            canvas.restore();
        }

        paint.setColor(Color.GREEN);
        int currentProgress = progress / count;
        for (int i = 0; i < currentProgress; i++) {
            canvas.save();
            int left = centerX - roundWidth / 2;
            int top = centerY - radius - roundHeight + 50;
            int right = left + roundWidth;
            int bottom = top + roundHeight;
            canvas.rotate(360 / count * i, centerX, centerY);
            canvas.drawRoundRect(left, top, right, bottom, rx, rx, paint);
            canvas.restore();
        }
    }

    @SuppressLint("unused")
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }
}
