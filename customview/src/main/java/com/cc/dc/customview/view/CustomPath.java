package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dc on 2017/12/5.
 */

public class CustomPath extends View {

    private Paint paint;

    private Path path;

    public CustomPath(Context context) {
        this(context, null);
    }

    public CustomPath(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);

        path = new Path();
//        path.moveTo(10, 10);
//        path.lineTo(10, 100);
////        path.lineTo(300, 100);
//        path.lineTo(500, 100);
//        path.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(200, 200, 600, 600);
        path.addRoundRect(rectF, 20, 20, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }
}
