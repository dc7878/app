package com.cc.dc.customview.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
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

    private Path path = new Path();

    private int alphaCount = 0;

    private Paint paintNew = new Paint(Paint.ANTI_ALIAS_FLAG);



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

        path.moveTo(centerX - 60, centerY - 20 + 40);
        path.lineTo(centerX - 20, centerY + 20 + 40);
        path.lineTo(centerX + 100, centerY - 80 + 40);

        paintNew.setColor(Color.GREEN);
        paintNew.setStrokeWidth(10);
        paintNew.setStyle(Paint.Style.STROKE);
        paintNew.setStrokeCap(Paint.Cap.ROUND);


        animatorBefore = ObjectAnimator.ofInt(this, "aa", 0, 100);
        animatorBefore.setDuration(500);
        animatorBefore.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progressStart = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
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
            drawLines(canvas, paint);
        } else {
            drawProgress(canvas);
            if (progress < 100) {
                progress += 5;
            } else {
                progressCircle = true;
            }
        }

        if (progressCircle) {
            drawCircle(canvas);
            if (circleSize >= radiusMax + 40) {
                if (alphaCount >= 255) {
                    alphaCount = 255;
                }
                alphaCount += 20;
                paintNew.setAlpha(alphaCount);
                drawLines(canvas, paintNew);
            } else {
                circleSize += 10;
            }
        }

        invalidate();
    }

    private void drawBeforeProgress(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(centerX, centerY, radiusMax + 5, paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, radiusMin + 5, paint);
    }

    private void drawLines(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPath(path, paint);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void drawProgress(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawArc(centerX - radiusMax, centerY - radiusMax,
                centerX + radiusMax, centerY + radiusMax, 90, progress * 360 / 100, false, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radiusMax, paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, radiusMax - circleSize, paint);
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
