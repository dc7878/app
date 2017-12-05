package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dc on 2017/12/5.
 */

public class CustomPath2 extends View {

    private Paint paint;

    private Path path;

    private float phase = 0;

    public CustomPath2(Context context) {
        this(context, null);
    }

    public CustomPath2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPath2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setStrokeJoin(Paint.Join.ROUND);

        path = new Path();
        path.moveTo(100, 600);
        path.lineTo(400, 100);
        path.lineTo(700, 900);

        new Thread(){
            @Override
            public void run() {
                while (true) {
                    phase += 10;
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        paint.setColor(Color.RED);
        paint.setPathEffect(null);
        canvas.drawPath(path, paint);

        paint.setPathEffect(new DashPathEffect(new float[]{20, 10, 100, 100}, phase));
        paint.setColor(Color.BLUE);
        canvas.translate(0, 100);
        canvas.drawPath(path, paint);
    }
}
