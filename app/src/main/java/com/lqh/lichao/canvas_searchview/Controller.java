package com.lqh.lichao.canvas_searchview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

/**
 * 图形绘制
 * Created by Administrator on 2017-10-11.
 */

public class Controller extends BaseController{
    private static final String TAG = "Controller";

    private String mColor = "#4CAF50";

    private int mCircleX;// 圆心

    private int mCircleY;

    private int mRadius;// 圆半径

    /**
     * 圆Rect区域
     */
    private RectF mRectF;

    private int mDeltaD = 15;
    public Controller() {
        mRectF = new RectF();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.parseColor(mColor));
        switch (mState) {
            case STATE_ANIM_NONE:
                drawNormalView(paint, canvas);
                break;
            case STATE_ANIM_START:
                drawStartAnimView(paint, canvas);
                break;
            case STATE_ANIM_STOP:
                drawNormalView(paint, canvas);
                break;
        }
    }

    /**
     * 停止绘制动画
     * @param paint
     * @param canvas
     */
    private void drawStopAnimView(Paint paint, Canvas canvas) {

    }

    /**
     * 开始动画绘制
     * @param paint
     * @param canvas
     */
    private void drawStartAnimView(Paint paint, Canvas canvas) {
        canvas.save();
        if(mpro <= 0.5) {
            /**
             * -360 ~ 0 需要变换的范围
             * 	 0  ~ 0.5 mpro实际的变化范围
             * 转换公式：360*(mpro*2-1),
             */
            //绘制圆
            canvas.drawArc(
                    mRectF,
                    45,
                    360*(mpro*2-1),
                    false,
                    paint
            );
            //绘制把手
            canvas.drawLine(
                    mRectF.right - mDeltaD,
                    mRectF.bottom - mDeltaD,
                    mRectF.right+ mRadius -mDeltaD,
                    mRectF.bottom+ mRadius -mDeltaD,
                    paint
            );
        } else {
            /**
             *   0    ~ 1 需要变换的范围
             * 	 0.5  ~ 1 mpro实际的变化范围
             * 转换公式：(mpro*2-1),
             */
            //绘制把手
            canvas.drawLine(
                    mRectF.right - mDeltaD + mRadius *(mpro*2-1),
                    mRectF.bottom - mDeltaD + mRadius *(mpro*2-1),
                    mRectF.right - mDeltaD + mRadius,
                    mRectF.bottom+ mRadius - mDeltaD,
                    paint);
        }
        //绘制下面的横线
        canvas.drawLine(
                (mRectF.right - mDeltaD + mRadius)*(1-mpro*0.8f),
                mRectF.bottom+ mRadius - mDeltaD,
                mRectF.right - mDeltaD + mRadius,
                mRectF.bottom+ mRadius - mDeltaD,
                paint);
        canvas.restore();

        mRectF.left = mCircleX - mRadius + mpro*250;
        mRectF.right = mCircleX + mRadius +mpro*250;
        mRectF.top = mCircleY - mRadius;
        mRectF.bottom = mCircleY + mRadius;
    }

    private void drawNormalView(Paint paint, Canvas canvas) {
        mRadius = getWidth()/20;
        Log.d(TAG, "mRadius = " + mRadius);
        mCircleX = getWidth()/2;
        mCircleY = getHeight()/2;

        mRectF.left = mCircleX - mRadius;
        mRectF.right= mCircleX + mRadius;
        mRectF.top = mCircleY - mRadius;
        mRectF.bottom = mCircleY + mRadius;

        canvas.save();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        canvas.rotate(45, mCircleX, mCircleY);
        canvas.drawLine(mCircleX + mRadius, mCircleY, mCircleX + mRadius *2, mCircleY, paint);
        canvas.drawArc(
                mRectF,
                0,
                360,
                false,
                paint);
        canvas.restore();
    }

    @Override
    public void startAnim() {
        super.startAnim();
        mState = STATE_ANIM_START;
        startViewAnimation();
    }

    @Override
    public void resetAnim() {
        super.resetAnim();
        mState = STATE_ANIM_STOP;
        startViewAnimation();
    }
}
