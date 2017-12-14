package com.cc.dc.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cc.dc.customview.R;
import com.cc.dc.customview.utils.ScreenUtil;

/**
 * Created by dc on 2017/12/13.
 * 一个自定义View 仿照芝麻信用分的
 * 参考文章：
 * http://blog.csdn.net/kong_gu_you_lan/article/details/52904064
 * 关于多边形的绘制：
 * http://www.barryzhang.com/archives/category/all/developer/android
 */

public class CustomCreditScore extends View {

    private Paint paint;

    private int centerX;
    private int centerY;
    private int radius = 300;

    private Path pathPolygon;

    private Path pathRegion;
    private Paint paintRegion;

    private Paint paintTxt;

    private Paint paintScore;

    // 各维度标题
    private String[] titles = {"身份特质", "履约能力", "信用历史", "人脉关系", "行为偏好"};
    // 各维度分数
    private float[] everyScore = {180, 170, 180, 160, 170};
    // 总分数
    private int currentScore = 860;
    // 各维度图片id
    private int[] ids = {R.drawable.ic_identity, R.drawable.ic_performance, R.drawable.ic_history, R.drawable.ic_contacts, R.drawable.ic_predilection};
    // 各维度图片
    private Bitmap[] bitmaps;

    private int count;

    private final int cellWidth = 120;
    private final int cellHeight = 180;

    private final int offsetX = 20;
    private final int offsetY = 0;

    public CustomCreditScore(Context context) {
        this(context, null);
    }

    public CustomCreditScore(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCreditScore(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        centerX = ScreenUtil.getScreenWidth(getContext()) / 2;
        centerY = ScreenUtil.getScreenHeight(getContext()) / 2;

        bitmaps = new Bitmap[ids.length];
        for (int i = 0; i < ids.length; i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(), ids[i]);
        }

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        pathPolygon = new Path();

        pathRegion = new Path();
        paintRegion = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRegion.setStrokeWidth(10);
        paintRegion.setStyle(Paint.Style.FILL);
        paintRegion.setStrokeJoin(Paint.Join.ROUND);

        paintTxt = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTxt.setTextSize(30);
        paintTxt.setColor(Color.YELLOW);

        paintScore = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintScore.setTextSize(50);
        paintScore.setColor(Color.WHITE);

        count = 5;
    }

    private void setData(String[] titles, int score, float[] everyScore, int[] ids) {
        if (titles.length < 3) {
            new IllegalArgumentException("至少需要三个标签");
        }
        if (everyScore.length < 3) {
            new IllegalArgumentException("至少需要三个维度的分数");
        }
        if (ids.length < 3) {
            new IllegalArgumentException("至少需要三个维度的图片");
        }
        if (titles.length != everyScore.length || titles.length != ids.length) {
            new IllegalArgumentException("数据不正常。。。");
        }
        if (score < 0 || score > 1000) {
            new IllegalArgumentException("分数不正常。。。");
        }
        this.titles = titles;
        this.currentScore = score;
        this.everyScore = everyScore;
        this.ids = ids;
        this.count = titles.length;

        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        drawPolygon(canvas);
        drawRegion(canvas);
        drawScore(canvas);
        drawText(canvas);
    }

    private void drawPolygon(Canvas canvas) {
        paint.setColor(Color.YELLOW);
        for (int i = 0; i < count; i++) {
            canvas.save();
            canvas.rotate(360 / count * i, centerX, centerY);
            pathPolygon.moveTo(centerX, centerY - radius);
            int offsetX = (int) (radius * Math.cos(1D * (90 - 360 / count) / 360 * 2 * Math.PI));
            int offsetY = (int) (radius * Math.sin((1D * 90 - 360 / count) / 360 * 2 * Math.PI));
            pathPolygon.lineTo(centerX + offsetX, centerY - offsetY);
            pathPolygon.lineTo(centerX, centerY);
            canvas.drawPath(pathPolygon, paint);
            canvas.restore();
        }
    }

    private void drawRegion(Canvas canvas) {
        paintRegion.setColor(Color.parseColor("#80C3F8"));

        canvas.save();
        canvas.translate(centerX, centerY);
        double a = 2 * Math.PI / count;
        for (int i = 0; i < count; i++) {
            int R = (int) (radius * everyScore[i] / 200);
            float x = (float) (R * Math.sin(a * i));
            float y = (float) (R * Math.cos(a * i)) * -1;
            if (i == 0){
                pathRegion.moveTo(x,y);
            }else{
                pathRegion.lineTo(x,y);
            }
        }
        pathRegion.close();
        canvas.drawPath(pathRegion, paintRegion);
        canvas.restore();
    }

    private void drawScore(Canvas canvas) {
        float width = paintScore.measureText(String.valueOf(currentScore));
        canvas.drawText(String.valueOf(currentScore),  centerX - width / 2, centerY + 20, paintScore);
    }

    private void drawText(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY);
        double a = 2 * Math.PI / count;
        for (int i = 0; i < count; i++) {
            float x = (float) (radius * Math.sin(a * i));
            float y = (float) (radius * Math.cos(a * i)) * -1;
            float width = paintTxt.measureText(titles[i]);
            float height = paintTxt.getFontMetrics().bottom - paintTxt.getFontMetrics().top;
            if (x > 0) {
                x += offsetX;
            }
            if (x < 0 && i != 3) {
                x -= cellWidth + offsetX;
            }
            if (i == 0) {
                x = - cellWidth/2;
                y -= cellHeight + offsetY;
            } else if (i == 2 || i == 3) {
                x -= cellWidth/2;
                y += offsetY;
            } else {
                y -= cellHeight / 2;
            }
            paintTxt.setColor(Color.YELLOW);
//            canvas.drawRect(x, y, x + cellWidth, y + cellHeight, paintTxt);
            float bitmapX = x + (cellWidth - bitmaps[i].getWidth()) / 2;
            float bitmapY = y + (cellHeight - bitmaps[i].getHeight() - height - 10) / 2;
            canvas.drawBitmap(bitmaps[i], bitmapX, bitmapY, paintTxt);

            paintTxt.setColor(Color.WHITE);
            canvas.drawText(titles[i], x + (cellWidth - width) / 2,cellHeight + y - height / 2, paintTxt);
        }
        canvas.restore();
    }
}
