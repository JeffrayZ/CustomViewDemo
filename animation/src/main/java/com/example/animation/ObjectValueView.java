package com.example.animation;

import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.animation
 * @ClassName: MyView
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/3/31 16:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/31 16:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ObjectValueView extends View {

    private int w;
    private int h;

    public ObjectValueView(Context context) {
        this(context, null);
    }

    public ObjectValueView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ObjectValueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ObjectValueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    private void init(Context context) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.BLUE);
        canvas.drawCircle(position.x <= 0 ? 40f : position.x, position.y <= 0 ? 40f : position.y, 40f, new Paint());
    }

    private PointF position;

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
        invalidate();
    }

    public void setPointFEvaluator() {
//        ObjectAnimator animator = ObjectAnimator.ofObject(this, "position",
//                new PointFEvaluator(), new PointF(40f, 40f), new PointF(600, 600));
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "position",
                new PointFEvaluator(), new PointF(40f, 40f), new PointF(600, 600));
        animator.setDuration(3000L);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }
}
