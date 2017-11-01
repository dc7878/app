package com.cc.dc.common.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dc on 2017/11/1.
 */
public class WaterView extends View {

    private Paint paint;

    private Path path;

    private int width;
    private int height;

    private float controlX = -50;
    private float controlY = -100;

    private float waterH = 430;

    private boolean flag = false;

    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);

        path.reset();
        path.moveTo(-width / 4, waterH);
        path.quadTo(controlX, controlY, width + width / 4, waterH);
        path.lineTo(width + width / 4, height);
        path.lineTo(-width / 4, height);
        canvas.drawPath(path, paint);

        if(flag) {
            controlX -= 20;
            if (controlX < -50) {
                flag = false;
            }
        } else {
            controlX += 20;
            if (controlX >= width) {
                flag = true;
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (waterH < height) {
            waterH += 2;
            controlY += 2;
        }
        invalidate();
    }
}
