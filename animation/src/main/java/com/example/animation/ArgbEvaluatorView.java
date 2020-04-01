package com.example.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.example.animation.typeevaluator.HsvEvaluator;

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
public class ArgbEvaluatorView extends View {
    public ArgbEvaluatorView(Context context) {
        this(context,null);
    }

    public ArgbEvaluatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArgbEvaluatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ArgbEvaluatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(color);
    }

    @ColorInt
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public void setArgbEvaluator(){
//        ObjectAnimator animator = ObjectAnimator.ofInt(this, "color", 0xffff0000, 0xff00ff00);
        ObjectAnimator animator = ObjectAnimator.ofArgb(this, "color", 0xffff0000, 0xff00ff00); // 5.0以后加入的
//        animator.setEvaluator(new ArgbEvaluator());
        animator.setEvaluator(new HsvEvaluator());
        animator.setDuration(5000L);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }
}
