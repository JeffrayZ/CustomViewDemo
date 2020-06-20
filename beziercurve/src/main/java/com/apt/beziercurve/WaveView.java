package com.apt.beziercurve;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

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
public class WaveView extends View {
    private Path path = new Path();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private int mItemWaveLength = 400;
    private int dx;
    private int dy;
    private Bitmap bitmap;
    // 将波纹绘制再这个bitmap上
    private Bitmap pathBitmap;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        pathBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSize(bitmap.getWidth(), widthMeasureSpec), resolveSize(bitmap.getHeight(), heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        int halfWaveLen = mItemWaveLength / 2;
        path.moveTo(-mItemWaveLength + dx, dy);
        for (int i = 0; i <= getWidth() + mItemWaveLength; i += mItemWaveLength) {
            path.rQuadTo(halfWaveLen / 2, -50, halfWaveLen, 0);
            path.rQuadTo(halfWaveLen / 2, 50, halfWaveLen, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();
        Canvas pathCanvas = new Canvas(pathBitmap);
        // 清空 pathBitmap 上的图像
        pathCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        pathCanvas.drawPath(path, paint);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        // 新建一个图层,背景默认透明
        int saveLayer = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null);
        canvas.drawBitmap(pathBitmap, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        paint.setXfermode(null);
        // 将新建的图层绘制到默认图层上
        canvas.restoreToCount(saveLayer);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(5000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                dy = getMeasuredHeight() * dx / 400;
                postInvalidate();
            }
        });
        animator.start();
    }
}

//        1.PorterDuff.Mode.CLEAR：清空混合区域，变为全透明
//        2.PorterDuff.Mode.SRC：显示上层绘制图片，下层也显示
//        3.PorterDuff.Mode.DST：只显示下层绘制图片
//        4.PorterDuff.Mode.SRC_OVER：正常绘制显示，上下层绘制叠盖。
//        5.PorterDuff.Mode.DST_OVER：上下层都显示。下层居上显示。
//        6.PorterDuff.Mode.SRC_IN：取两层绘制交集。显示上层。
//        7.PorterDuff.Mode.DST_IN：取两层绘制交集。显示下层。
//        8.PorterDuff.Mode.SRC_OUT：取上层绘制非交集部分。显示下层
//        9.PorterDuff.Mode.DST_OUT：取下层绘制非交集部分。不显示上层
//        10.PorterDuff.Mode.SRC_ATOP：取下层非交集部分与上层交集部分
//        11.PorterDuff.Mode.DST_ATOP：取上层非交集部分与下层交集部分
//        12.PorterDuff.Mode.XOR：异或：去除两图层交集部分
//        13.PorterDuff.Mode.DARKEN：取两图层全部区域，交集部分颜色加深
//        14.PorterDuff.Mode.LIGHTEN：取两图层全部，点亮交集部分颜色
//        15.PorterDuff.Mode.MULTIPLY：取两图层交集部分叠加后颜色
//        16.PorterDuff.Mode.SCREEN：取两图层全部区域，交集部分变为透明色

