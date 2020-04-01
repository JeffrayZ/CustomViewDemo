package com.example.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
public class MyView extends View {
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.BLUE);
    }
}
