package com.lqh.lichao.canvas_searchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制搜索动画
 * Created by Administrator on 2017-10-11.
 */

public class MySearchView extends View {

    private Paint mPaint;
    private BaseController mController;

    public MySearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
    }

    public void setController(BaseController controller){
        this.mController = controller;
        mController.setSearchView(this);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mController.draw(canvas, mPaint);
    }

    public void startAnimation(){
        if(mController!=null){
            mController.startAnim();
        }
    }

    public void resetAnimation(){
        if(mController!=null){
            mController.resetAnim();
        }
    }
}
