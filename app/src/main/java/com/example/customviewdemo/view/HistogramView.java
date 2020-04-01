package com.example.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.customviewdemo.view
 * @ClassName: HistogramView
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/4/1 13:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/1 13:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HistogramView extends View {
    private Paint paint;
    private Path path;
    private int defaultWidth = 800;
    private int defaultHeight = 900;
    private Rect rect;
    private String chi = "语文";
    private String mat = "数学";
    private String eng = "英语";

    public HistogramView(Context context) {
        super(context);
        init(context);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        path = new Path();
        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSize(defaultWidth,widthMeasureSpec),resolveSize(defaultHeight,heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.BLUE);

        // 横纵坐标
        canvas.save();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(50,870,50,50,paint);
        canvas.drawLine(30,850,750,850,paint);
        canvas.restore();

        canvas.save();
        paint.reset();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(100,100,200,850,paint);
        canvas.drawRect(300,350,400,850,paint);
        canvas.drawRect(500,250,600,850,paint);
        canvas.restore();

        canvas.save();
        paint.reset();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(40f);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(chi,0,chi.length(),rect);
        canvas.drawText(chi,100,850 + rect.height(),paint);
        paint.getTextBounds(mat,0,mat.length(),rect);
        canvas.drawText(mat,300,850 + rect.height(),paint);
        paint.getTextBounds(eng,0,eng.length(),rect);
        canvas.drawText(eng,500,850 + rect.height(),paint);
        canvas.restore();
    }
}
