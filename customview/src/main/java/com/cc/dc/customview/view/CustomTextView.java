package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dc on 2017/12/5.
 */

public class CustomTextView extends View {

    private Paint paint;

    private Rect rect = new Rect();

    private String text = "测试赛叔叔JLJjlPqpnmMlL";

    private float x;

    private float y;

    private Paint paintText = new Paint();

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.RED);
        paintText.setTextSize(60);

        rect.set(100, 100, 1000, 400);

        Paint.FontMetricsInt fontMetricsInt = paintText.getFontMetricsInt();
        x = (1000 - 100 - paintText.measureText(text)) / 2 + 100;
        y = rect.centerY() + (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);

        paint.setColor(Color.YELLOW);
        canvas.drawLine(100, rect.centerY(), 1000, rect.centerY(), paint);

        canvas.drawText(text, x, y , paintText);
    }
}
