package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaActionSound;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cc.dc.customview.R;

/**
 * Created by dc on 2017/12/7.
 */

public class CustomClipView1 extends View {

    private Bitmap bitmap;

    private Paint paint;

    private Matrix matrix;

    public CustomClipView1(Context context) {
        this(context, null);
    }

    public CustomClipView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomClipView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);

        paint = new Paint();

        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);


        matrix.postTranslate(100, 100);
        canvas.save();
        canvas.concat(matrix);
//        canvas.clipRect(0, 0, 500, 500);
//        canvas.rotate(45, 0, 0);
//        canvas.translate(100, 100);
//        canvas.scale(0.5f, 1.5f);
//        canvas.skew(0, 0.5f);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

//        paint.setColor(Color.RED);
//        canvas.drawRect(0, 0, 200, 200, paint);
    }
}
