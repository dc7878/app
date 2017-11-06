package com.cc.dc.customview.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dc on 17/11/6.
 */

public class CustomCircleProgress extends View {

    private Paint paint;

    private int size = 400;

    private int centerX;
    private int centerY;

    private int radiusMax;
    private int radiusMin;

    private boolean progressStart = false;

    private boolean progressCircle = false;


    private int progress = 0;

    private int circleSize;


    private ObjectAnimator animatorBefore;


    private ObjectAnimator animatorProgress;

    private ObjectAnimator animatorCircle;

    public CustomCircleProgress(Context context) {
        this(context, null);
    }

    public CustomCircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        centerX = size / 2;
        centerY = size / 2;

        radiusMax = (size - 50) /2;
        radiusMin = (size - 70) / 2;

        animatorBefore = ObjectAnimator.ofInt(this, "aa", 0, 100);
        animatorBefore.setDuration(500);
        animatorBefore.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progressStart = true;
                animatorProgress.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        animatorProgress = ObjectAnimator.ofInt(this, "progress", 0, 100);
        animatorProgress.setDuration(500);

        animatorProgress.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progressCircle = true;
                animatorCircle.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        animatorCircle = ObjectAnimator.ofInt(this, "circleSize", 0, radiusMax - 5);
        animatorCircle.setDuration(600);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animatorBefore.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animatorBefore.cancel();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        if (!progressStart) {
            drawBeforeProgress(canvas);
        }

        drawProgress(canvas);

        if (progressCircle) {
            drawCircle(canvas);
        }
    }

    private void drawBeforeProgress(Canvas canvas) {
        paint.setColor(Color.GRAY);
        canvas.drawCircle(centerX, centerY, radiusMax, paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, radiusMin, paint);
    }

    private void drawProgress(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawArc(centerX - radiusMax, centerY - radiusMax,
                centerX + radiusMax, centerY + radiusMax, 90, progress * 360 / 100, false, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawCircle(centerX, centerY, radiusMax - 5, paint);


        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, radiusMax - 5 - circleSize, paint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setCircleSize(int circleSize) {
        this.circleSize = circleSize;
        invalidate();
    }
}
