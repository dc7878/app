package com.cc.dc.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by dc on 2017/12/14.
 */

@SuppressLint("AppCompatCustomView")
public class CustomRandomTextView extends TextView {

    private boolean isFirst = true;

    private Paint paint;

    private float baseLine;

    private String txt;
    private String txtNew = "5678";

    private float offsetY;

    private float changeY = 10;

    private boolean isUp = true;

    private boolean flag = true;

    private final int CHAR_W = 65;

    public CustomRandomTextView(Context context) {
        this(context, null);
    }

    public CustomRandomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRandomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        new Thread(){
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(30);
                        if (isUp) {
                            offsetY -= changeY;
                            if (offsetY < -200) {
                                offsetY = 200;
                                isUp = false;
                                flag = false;
                            }
                        } else {
                            offsetY -= changeY;
                            if (offsetY < 0) {
                                offsetY = 0;
                                isUp = true;
                            }
                        }
                        postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        if (isFirst) {
            isFirst = false;
            paint = getPaint();
            txt = getText().toString().trim();

            baseLine = getMeasuredHeight() / 2 + (paint.getFontMetrics().bottom - paint.getFontMetrics().top) / 2 - paint.getFontMetrics().bottom;
            invalidate();
        }
        canvas.drawLine(0, baseLine, getMeasuredWidth(), baseLine, paint);
        if (flag) {
            if (txt.length() == txtNew.length()) {
                // 位数相同
                int len = txt.length();
                for (int i = 0; i < len; i++) {
                    if (txt.charAt(i) != txtNew.charAt(i)) {
                        canvas.drawText(String.valueOf(txt.charAt(i)), CHAR_W * i, baseLine + offsetY, paint);
                        canvas.drawText(String.valueOf(txtNew.charAt(i)), CHAR_W * i, baseLine + offsetY + 200, paint);
                    } else {
                        canvas.drawText(String.valueOf(txt.charAt(i)), CHAR_W * i, baseLine, paint);
                    }
                }
            } else {
                // 位数不同 一般都是new大于current
                for (int i = 0; i < txtNew.length(); i++) {
                    if (i == txt.length()) {
                        canvas.drawText("", CHAR_W * i, baseLine + offsetY, paint);
                        canvas.drawText(String.valueOf(txtNew.charAt(i)), CHAR_W * i, baseLine + offsetY + 200, paint);
                    } else {
                        if (txt.charAt(i) != txtNew.charAt(i)) {
                            canvas.drawText(String.valueOf(txt.charAt(i)), CHAR_W * i, baseLine + offsetY, paint);
                            canvas.drawText(String.valueOf(txtNew.charAt(i)), CHAR_W * i, baseLine + offsetY + 200, paint);
                        }
                    }
                }
                // TODO: 2017/12/14 new.length > txtNew.length
            }
        } else {
            for (int i = 0; i < txtNew.length(); i++) {
                canvas.drawText(String.valueOf(txtNew.charAt(i)), CHAR_W * i, baseLine, paint);
            }
        }
    }

    public void reset() {
        if (flag) {
            return;
        }
        txtNew = String.valueOf(Integer.valueOf(txt) + 1);
        flag = true;
        isUp = true;
        offsetY = 0;
        init();
    }
}
