package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cc.dc.customview.R;


/**
 * Created by dc on 2017/12/5.
 * 注水效果
 */

public class CustomView2 extends View {

    private Bitmap bitmapDst;

    private Bitmap bitmapSrc;

    private Paint paint;

    private Xfermode xfermode;

    private int height = 2;

    private Path path = new Path();

    public CustomView2(Context context) {
        this(context, null);
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmapSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmapDst = Bitmap.createBitmap(bitmapSrc.getWidth(), bitmapSrc.getHeight(), Bitmap.Config.ARGB_8888);
        pathInit();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        height += 5;
                        Thread.sleep(100);
                        pathInit();
                        postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void pathInit() {
        path.reset();
        path.moveTo(0, bitmapDst.getHeight() - height);
        path.lineTo(bitmapDst.getWidth(), bitmapDst.getHeight() - height);
        path.lineTo(bitmapDst.getWidth(), bitmapDst.getHeight());
        path.lineTo(0, bitmapDst.getHeight());
        path.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Canvas c = new Canvas(bitmapDst);
        c.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        c.drawPath(path, new Paint());

        canvas.drawBitmap(bitmapSrc, 0, 0, paint);

        int layerId = canvas.saveLayer(0, 0, bitmapSrc.getWidth(), bitmapSrc.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmapDst, 0, 0, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmapSrc, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
