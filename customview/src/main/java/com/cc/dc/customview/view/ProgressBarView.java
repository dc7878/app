package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dc on 17/12/12.
 */

public class ProgressBarView extends View {

    private Paint paint;

    private int currentProgress;

    public ProgressBarView(Context context) {
        this(context, null);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        paint.setColor(Color.YELLOW);
        canvas.drawLine(100, 100, 600, 100, paint);

        paint.setColor(Color.RED);
        canvas.drawLine(100, 100, 100 + 5 * currentProgress, 100, paint);
    }

    public void updateProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        postInvalidate();
    }
}
