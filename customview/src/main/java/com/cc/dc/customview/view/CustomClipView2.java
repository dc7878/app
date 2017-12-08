package com.cc.dc.customview.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.cc.dc.customview.R;

/**
 * Created by dc on 2017/12/7.
 */

public class CustomClipView2 extends View {

    private Bitmap bitmap;

    private Paint paint;

    private Matrix matrix;

    private Camera camera;

    private float x = 200;

    private float y = 300;

    private int degree;

    ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 360);

    public CustomClipView2(Context context) {
        this(context, null);
    }

    public CustomClipView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomClipView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public int getDegree() {
        return degree;
    }

    @SuppressWarnings("unused")
    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        paint = new Paint();

        matrix = new Matrix();

        camera = new Camera();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = - displayMetrics.density * 6;
        camera.setLocation(0, 0, newZ);
        Log.e("CustomClipView2", "CustomClipView2>>>" + newZ);

        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        canvas.save();

        camera.save();
        camera.rotateX(degree);
        canvas.translate(bitmap.getWidth() / 2 + x, bitmap.getHeight() / 2 + y);
        camera.applyToCanvas(canvas);
        canvas.translate(-bitmap.getWidth() / 2 - x, -bitmap.getHeight() / 2 - y);
        camera.restore();

        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();
    }
}
