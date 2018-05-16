package com.cc.dc.customview.marquee.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dc on 2018/3/30.
 */

public class TextViewAd extends TextView {

    private int mDuration;
    private int mInterval;
    private List<ADEnity> mTexts;
    private int mY = 0;
    private int mIndex = 0;
    private Paint mPaintBack;
    private Paint mPaintFront;
    private boolean isMove = true;
    private String TAG = "ADTextView";
    private boolean hasInit = false;

    public interface onClickLitener {
        public void onClick(String mUrl);
    }

    private onClickLitener onClickLitener;

    public void setOnClickLitener(TextViewAd.onClickLitener onClickLitener) {
        this.onClickLitener = onClickLitener;
    }

    public TextViewAd(Context context) {
        this(context, null);
    }

    public TextViewAd(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (onClickLitener != null) {
                    onClickLitener.onClick(mTexts.get(mIndex).getmUrl());
                }
                break;
        }
        return true;
    }


    public void setmTexts(List mTexts) {
        this.mTexts = mTexts;
        invalidate();
    }


    public void setmInterval(int mInterval) {
        this.mInterval = mInterval;
    }


    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }


    public void setFrontColor(int mFrontColor) {
        mPaintFront.setColor(mFrontColor);
    }


    public void setBackColor(int mBackColor) {
        mPaintBack.setColor(mBackColor);
    }


    private void init() {
        mDuration = 1000;
        mInterval = 1000;
        mIndex = 0;
        mPaintFront = new Paint();
        mPaintFront.setAntiAlias(true);
        mPaintFront.setDither(true);
        mPaintFront.setTextSize(30);


        mPaintBack = new Paint();
        mPaintBack.setAntiAlias(true);
        mPaintBack.setDither(true);
        mPaintBack.setTextSize(30);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: " + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mTexts != null) {
            Log.i(TAG, "onDraw: " + mY);
            ADEnity model = mTexts.get(mIndex);
            String font = model.getmFront();
            String back = model.getmBack();

            Rect indexBound = new Rect();
            mPaintFront.getTextBounds(font, 0, font.length(), indexBound);


            Rect contentBound = new Rect();
            mPaintBack.getTextBounds(back, 0, back.length(), contentBound);
            if (mY == 0 && hasInit == false) {
                mY = getMeasuredHeight() - indexBound.top;
                hasInit = true;
            }

            if (mY == 0 - indexBound.bottom) {
                Log.i(TAG, "onDraw: " + getMeasuredHeight());
                mY = getMeasuredHeight() - indexBound.top;
                mIndex++;
            }
            canvas.drawText(back, 0, back.length(), (indexBound.right - indexBound.left) + 20, mY, mPaintBack);
            canvas.drawText(font, 0, font.length(), 10, mY, mPaintFront);

            if (mY == getMeasuredHeight() / 2 - (indexBound.top + indexBound.bottom) / 2) {
                isMove = false;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        postInvalidate();
                        isMove = true;
                    }
                }, mInterval);
            }
            mY -= 1;

            if (mIndex == mTexts.size()) {
                mIndex = 0;
            }


            if (isMove) {
                postInvalidateDelayed(mDuration / getMeasuredHeight());
            }
        }

    }
}
