package com.cc.dc.common.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * Created by dc on 2017/10/26.
 */
public class CircleImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;

    private static final int DEFAULT_SIZE = 2;

    private int radius = 0;
    private int width;
    private int height;
    private Bitmap bm;

    private int realSize = 180;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        Bitmap bm = getBitmapFromDrawable(getDrawable());
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        bm = getBitmapFromDrawable(drawable);
        initSize(bm);
        invalidate();
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        Bitmap bm = getBitmapFromDrawable(getDrawable());
    }

    private void initSize(Bitmap bm) {
        width = bm.getWidth();
        height = bm.getHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        int layer = canvas.saveLayer(0, 0, width, height, paint);
        super.onDraw(canvas);
        radius = Math.min(realSize, Math.min(width, height)) / 2;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(createBitmap(), 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layer);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        realSize = Math.min(w, h);
    }

    private Bitmap createBitmap() {
        int size = realSize;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);
        canvas.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, radius, paint);
        return bitmap;
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(DEFAULT_SIZE, DEFAULT_SIZE, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
