package com.cc.dc.customview.view;

import android.annotation.TargetApi;
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
import android.view.View;

import com.cc.dc.customview.R;


/**
 * Created by dc on 2017/12/5.
 * 圆形、三角形图片
 */

public class CustomView1 extends View {

    private Bitmap bitmapDst;

    private Bitmap bitmapSrc;

    private Paint paint;

    public CustomView1(Context context) {
        this(context, null);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

        bitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        bitmapSrc = createSrcBitmap();
    }

    private Bitmap createSrcBitmap() {
        Bitmap bitmapSrc = Bitmap.createBitmap(bitmapDst.getWidth() , bitmapDst.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapSrc);
        Paint paintSrc = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paintSrc.setStyle(Paint.Style.FILL);

        //3.三角图片
        Path path = new Path();
        path.moveTo(bitmapDst.getWidth() / 2, 0);
        path.lineTo(0, bitmapDst.getHeight());
        path.lineTo(bitmapSrc.getWidth(), bitmapSrc.getHeight());
        path.close();
        canvas.drawPath(path, paintSrc);

        // 2.圆角图片
//        Path path = new Path();
//        RectF rectF = new RectF(0, 0, bitmapDst.getWidth(), bitmapDst.getHeight());
//        path.addRoundRect(rectF, 60, 60, Path.Direction.CW);
//        canvas.drawPath(path, paintSrc);

        //1. 圆形图片
//        canvas.drawCircle(bitmapSrc.getWidth() / 2, bitmapSrc.getHeight() / 2, bitmapSrc.getWidth() / 2, paintSrc);
        return bitmapSrc;
    }

    @TargetApi(21)
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        int layer = canvas.saveLayer(0, 0, bitmapDst.getWidth(), bitmapDst.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmapDst, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapSrc, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layer);
    }
}
