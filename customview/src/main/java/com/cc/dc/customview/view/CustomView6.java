package com.cc.dc.customview.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

/**
 * Created by dc on 2017/12/8.
 */

public class CustomView6 extends View {

    private final String TAG = "CustomView6";

    private int lastColumnIndex = -1;
    private int lastRowIndex = -1;

    private Paint paint;

    private Path realPath;
    private Path path;
    private Paint paintPath;

    private int screenW;
    private int screenH;

    private int column = 4;
    private int row = 10;
    private int columnSpace = 5;
    private int rowSpace = 10;

    private int cellW;
    private int cellH;

    private HashMap<String, Float> map;

    public CustomView6(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = getWidth();
        screenH = getHeight();

        cellW = (screenW - (column - 1) * columnSpace) / column;
        cellH = (screenH - (row - 1) * rowSpace) / row;
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paintPath = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintPath.setColor(Color.YELLOW);
        paintPath.setStyle(Paint.Style.STROKE);
        paintPath.setStrokeWidth(20);
        // 两端是圆头
        paintPath.setStrokeCap(Paint.Cap.ROUND);
        // 设置连接处是圆头
        paintPath.setStrokeJoin(Paint.Join.ROUND);

        path = new Path();
        realPath = new Path();

        map = new HashMap<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        drawCell(canvas);
        drawPath(canvas);
    }

    private void drawCell(Canvas canvas) {
        paint.setColor(Color.RED);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                canvas.drawRect(
                        j * (cellW + columnSpace),
                        i * (cellH + rowSpace),
                        (j + 1) * cellW + j * columnSpace,
                        (i + 1) * cellH + i * rowSpace, paint);
            }
        }
    }

    private void drawPath(Canvas canvas) {
        if (!path.isEmpty()) {
            canvas.drawPath(path, paintPath);
        }
        if (!realPath.isEmpty()) {
            canvas.drawPath(realPath, paintPath);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                reset();
                revisesStartPoint(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                revisesMovePoint(event.getX(), event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reset();
                        postInvalidate();
                    }
                }, 500);
                break;
        }
        return super.onTouchEvent(event);
    }

    private void reset() {
        path.reset();
        realPath.reset();
        lastColumnIndex = -1;
        lastRowIndex = -1;
        map.clear();
    }

    private void revisesStartPoint(float pointX, float pointY) {
        int column = (int) Math.ceil(pointX / (columnSpace + cellW));
        int row = (int) Math.ceil(pointY / (rowSpace + cellH));

        float centerX = (column - 1) * (cellW + columnSpace) + cellW / 2;
        float centerY = (row - 1) * (cellH + rowSpace) + cellH / 2;

        path.moveTo(centerX, centerY);
        realPath.moveTo(centerX, centerY);
        lastColumnIndex = column;
        lastRowIndex = row;
        map.put(row + "" + column, centerX);
    }

    private void revisesMovePoint(float pointX, float pointY) {
        int column = (int) Math.ceil(pointX / (columnSpace + cellW));
        int row = (int) Math.ceil(pointY / (rowSpace + cellH));

        // 同一个点
        if (column == lastColumnIndex && row == lastRowIndex) {
            return;
        }
        // 同一列
        if (column == lastColumnIndex) {
            if (row != lastRowIndex - 1 && row != lastRowIndex + 1) {
                return;
            }
        }
        // 同一行
        if (row == lastRowIndex) {
            if (column != lastColumnIndex - 1 && column != lastColumnIndex + 1) {
                return;
            }
        }
        // 不同行不同列
        if (column != lastColumnIndex && row != lastRowIndex) {
            return;
        }
        // 已经有了
        if (map.containsKey(row + "" + column)) {
            return;
        }

        Log.e("CustomView6", "CustomView6>>>" + lastRowIndex + ">" + lastColumnIndex + ">" + column + ">" + row);

        float centerX = (column - 1) * (cellW + columnSpace) + cellW / 2;
        float centerY = (row - 1) * (cellH + rowSpace) + cellH / 2;

        realPath.lineTo(centerX, centerY);
        if (map.size() > 0) {
            path.reset();
//            path.moveTo(centerX, centerY);
//            path.lineTo(pointX, pointY);
        }
        lastColumnIndex = column;
        lastRowIndex = row;
        map.put(row + "" + column, centerX);
    }
}
