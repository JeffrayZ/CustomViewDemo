package com.apt.beziercurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.apt.beziercurve
 * @ClassName: Normal
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/6/20 10:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/20 10:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Normal extends View {
    private Path path = new Path();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public Normal(Context context) {
        super(context);
        init();
    }

    public Normal(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Normal(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Normal(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSize(600, widthMeasureSpec), resolveSize(600, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        path.cubicTo(); // 三阶
//        path.rCubicTo(); // 三阶

//        path.quadTo(); // 二阶
//        path.rQuadTo(); // 二阶

        // 移动到点(100,300)
        path.moveTo(100f, 300f);
        // 点(100,300) 到 点(300,300)以控制点(200,200)画曲线
        path.quadTo(200f, 200f, 300f, 300f);
        // 点(300,300) 到 点(500,300)以控制点(400,400)画曲线
        path.quadTo(400f, 400f, 500f, 300f);
        canvas.drawPath(path, paint);

        path.reset();
        paint.setColor(Color.BLUE);
        path.moveTo(100f, 350f);
        // 控制点 100+100，350+(-100)       终点 100+200，350+0
        path.rQuadTo(100f, -100f, 200f, 0f);
        // 控制点 100+200+100，350+0+100  终点 100+200+200，350+0+0
        path.rQuadTo(100f, 100f, 200f, 0f);
        canvas.drawPath(path, paint);
    }
}
