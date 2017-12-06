package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cc.dc.customview.R;

/**
 * Created by dc on 2017/12/6.
 * 刮卡效果
 */

public class CustomView4 extends View {

    private Bitmap bitmapDst;
    private Bitmap bitmapSrc;
    private Bitmap bitmapTxt;

    private Paint paint;
    private Path path;

    private float preX;
    private float preY;

    public CustomView4(Context context) {
        this(context, null);
    }

    public CustomView4(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmapTxt = BitmapFactory.decodeResource(getResources(), R.drawable.gua_txt);
        bitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.gua);
        bitmapDst = Bitmap.createBitmap(bitmapSrc.getWidth(), bitmapSrc.getHeight(), Bitmap.Config.ARGB_8888);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapTxt, 0, 0, paint);

        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        Canvas c = new Canvas(bitmapDst);
        c.drawPath(path, paint);

        canvas.drawBitmap(bitmapDst, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(bitmapSrc, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = event.getX();
                preY = event.getY();
                path.moveTo(preX, preY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (preX + event.getX()) / 2;
                float endY = (preY + event.getY()) / 2;
                path.quadTo(preX, preY, endX, endY);
                preX = event.getX();
                preY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
