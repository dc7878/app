package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cc.dc.customview.R;

/**
 * Created by dc on 2017/12/6.
 */

public class CustomView5 extends View {

    private Bitmap bitmapDst;
    private Bitmap bitmapSrc;

    private Paint paint;

    private float centerX;
    private float centerY;

    private Xfermode xfermode;

    private final int RADIUS = 200;

    public CustomView5(Context context) {
        this(context, null);
    }

    public CustomView5(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.bj);
        bitmapSrc = Bitmap.createBitmap(bitmapDst.getWidth(), bitmapDst.getHeight(), Bitmap.Config.ARGB_8888);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Canvas c = new Canvas(bitmapSrc);
        c.drawColor(Color.GREEN, PorterDuff.Mode.CLEAR);
        paint.setColor(Color.RED);
        c.drawCircle(centerX, centerY, RADIUS, paint);

        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        paint.setColor(Color.YELLOW);
        canvas.drawBitmap(bitmapDst, 0, 0, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmapSrc, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                centerX = event.getX();
                centerY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                centerX = event.getX();
                centerY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
