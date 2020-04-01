package com.example.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.animation
 * @ClassName: SportsView
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/3/31 17:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/31 17:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
//  ObjectAnimator
//        使用方式：
//
//        1、如果是自定义控件，需要添加 setter / getter 方法；
//        2、用 ObjectAnimator.ofXXX() 创建 ObjectAnimator 对象；
//        3、用 start() 方法执行动画。
public class SportsView extends View {
    float progress = 0;
    RectF arcRectF;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    // 创建 getter 方法
    public float getProgress() {
        return progress;
    }

    // 创建 setter 方法
    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        arcRectF = new RectF(8, 8, w -8, h -8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawArc(arcRectF, 135, progress * 3.6f, false, paint);

        paint.reset();
        paint.setColor(Color.RED);
        paint.setTextSize(48f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawText((int)progress+"%",getWidth()/2,getHeight()/2,paint);
    }
}
