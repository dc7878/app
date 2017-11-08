package com.cc.dc.customview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
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

    private int progress = 0;

    private int circleSize = 0;

    private Path path = new Path();

    private int alphaCount = 0;

    private Paint paintNew = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int scaleCounter = 60;
    private Paint paintLast = new Paint(Paint.ANTI_ALIAS_FLAG);

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

        postDelayed(new Runnable() {
            @Override
            public void run() {
                progressStart = true;
                postInvalidate();
            }
        }, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        if (!progressStart) {
            drawBeforeProgress(canvas);
            drawLines(canvas, paint);
            return;
        }
        drawProgress(canvas);
        if (progress < 100) {
            progress += 5;
            invalidate();
            return;
        }

        drawCircle(canvas);
        if (circleSize >= radiusMax + 40) {
            if (alphaCount >= 255) {
                alphaCount = 255;
            }
            alphaCount += 20;
            paintNew.setAlpha(alphaCount);
            drawLines(canvas, paintNew);

            scaleCounter -= 2;
            if (scaleCounter <= -60) {
                scaleCounter = -60;
            }
            drawArc(canvas);
            if (scaleCounter != -60) {
                invalidate();
            }
        } else {
            circleSize += 10;
            invalidate();
        }
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void drawArc(Canvas canvas) {
        float strokeWith = paintLast.getStrokeWidth() + (scaleCounter > 0 ? 2 : -2);
        paintLast.setColor(Color.RED);
        paintLast.setStyle(Paint.Style.STROKE);
        paintLast.setStrokeWidth(strokeWith);
        canvas.drawArc(centerX - radiusMax, centerY - radiusMax,
                centerX + radiusMax, centerY + radiusMax, 0, 360, false, paintLast);
    }

    private void drawCircle(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radiusMax, paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, radiusMax - circleSize, paint);
    }

    @SuppressWarnings("unused")
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @SuppressWarnings("unused")
    public void setCircleSize(int circleSize) {
        this.circleSize = circleSize;
        invalidate();
    }

    public void reset() {
        progress = 0;
        circleSize = 0;
        scaleCounter = 60;
        progressStart = false;
        alphaCount = 0;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                progressStart = true;
                postInvalidate();
            }
        }, 500);
    }
}
