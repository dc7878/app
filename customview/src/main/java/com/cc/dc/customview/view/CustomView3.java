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
import android.view.View;

/**
 * Created by dc on 2017/12/6.
 * PorterDuff.Mode使用测试
 */

public class CustomView3 extends View {

    private Bitmap bitmapDst;
    private Bitmap bitmapSrc;

    private Paint paint;

    private int width = 400;
    private int height = 400;

    private float sizeScale = 0.75f;

    private Xfermode xfermode;

    public CustomView3(Context context) {
        this(context, null);
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapDst = createBitmapDst();
        bitmapSrc = createBitmapSrc();
    }

    private Bitmap createBitmapDst() {
        Bitmap bitmapDst = Bitmap.createBitmap((int)(width * sizeScale), (int)(height * sizeScale), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapDst);
        paint.setColor(Color.RED);
        canvas.drawRect(0, 0, bitmapDst.getWidth(), bitmapDst.getHeight(), paint);
        return bitmapDst;
    }

    private Bitmap createBitmapSrc() {
        Bitmap bitmapSrc = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapSrc);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(width - sizeScale * width/ 2, height - sizeScale * width/ 2, sizeScale * width/ 2, paint);
        return bitmapSrc;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmapDst, 0, 0, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmapSrc, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layer);
    }
}
