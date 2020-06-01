package com.example.canvasbasedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.canvasbasedemo
 * @ClassName: MyView1
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/3/30 15:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/30 15:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyView1 extends View {

    public MyView1(Context context) {
        this(context, null);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.coco2dxcplus);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private Paint paint = new Paint();
    private Path path = new Path();
    private Bitmap bitmap;
    private Bitmap bitmap1;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制一个圆
//        canvas.drawCircle(300, 300, 200, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(100, 100, 400, 400, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(600, 100, 900, 400, paint);


//        paint.setStrokeWidth(80);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(100, 100, paint);
//
//        paint.setStrokeWidth(80);
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawPoint(200, 200, paint);
//
//        paint.setStrokeWidth(80);
//        paint.setStrokeCap(Paint.Cap.BUTT);
//        canvas.drawPoint(300, 300, paint);

        // 分割线================================================================

//        paint.setStrokeWidth(20);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
//        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
//        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
//                8 /* 一共绘制 8 个数（4 个点）*/, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawOval(50, 50, 350, 200, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawOval(400, 50, 700, 200, paint);

        // 分割线================================================================

//        paint.setStrokeWidth(20f);
//        canvas.drawLine(200, 200, 800, 500, paint);

        // 分割线================================================================

//        paint.setStrokeWidth(5f);
//        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20,
//                150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
//        canvas.drawLines(points, paint);

        // 分割线================================================================

//        paint.setStrokeWidth(5f);
//        canvas.drawRoundRect(100, 100, 500, 300, 50, 50, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRoundRect(600, 100, 1000, 300, 50, 50, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.FILL); // 填充模式
//        canvas.drawArc(200, 100, 800, 500, -110, 100, true, paint); // 绘制扇形
//        canvas.drawArc(200, 100, 800, 500, 20, 140, false, paint); // 绘制弧形
//        paint.setStyle(Paint.Style.STROKE); // 画线模式
//        canvas.drawArc(200, 100, 800, 500, 180, 60, false, paint); // 绘制不封口的弧形

        // 分割线================================================================

        // 使用 path 对图形进行描述（这段描述代码不必看懂）
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(400, 542);
//        canvas.drawPath(path, paint); // 绘制出 path 描述的图形（心形），大功告成

        // 分割线================================================================

//        path.addCircle(300, 300, 200, Path.Direction.CW);
//        canvas.drawPath(path, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.STROKE);
//        path.lineTo(100, 100); // 由当前位置 (0, 0) 向 (100, 100) 画一条直线
//        path.rLineTo(100, 0); // 由当前位置 (100, 100) 向正右方 100 像素的位置画一条直线
//        canvas.drawPath(path, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.STROKE);
//        path.lineTo(100, 100); // 画斜线
//        path.moveTo(200, 100); // 我移~~
//        path.lineTo(200, 0); // 画竖线
//        canvas.drawPath(path, paint);

        // 分割线================================================================
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5f);
//        path.lineTo(100, 100);
//        path.arcTo(100, 100, 300, 300, -90, 90, true); // 强制移动到弧形起点（无痕迹）
//        canvas.drawOval(100, 100, 300, 300,paint);
//        paint.setColor(Color.RED);
//        canvas.drawPath(path, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5f);
//        path.lineTo(100, 100);
//        path.arcTo(100, 100, 300, 300, -90, 90, false); // 直接连线连到弧形起点（有痕迹）
//        canvas.drawOval(100, 100, 300, 300,paint);
//        paint.setColor(Color.RED);
//        canvas.drawPath(path, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.STROKE);
//        path.lineTo(100, 100);
//        path.addArc(100, 100, 300, 300, -90, 90);
//        canvas.drawPath(path, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.STROKE);
//        path.moveTo(100, 100);
//        path.lineTo(200, 100);
//        path.lineTo(150, 150);
//        path.close(); // 使用 close() 封闭子图形。等价于 path.lineTo(100, 100)
//        canvas.drawPath(path,paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.FILL);
//        path.moveTo(100, 100);
//        path.lineTo(200, 100);
//        path.lineTo(150, 150);
//        // 这里只绘制了两条边，但由于 Style 是 FILL ，所以绘制时会自动封口
//        canvas.drawPath(path,paint);

        // 分割线================================================================

//        canvas.drawBitmap(bitmap, 200, 100, paint);

        // 分割线================================================================

//        canvas.drawText("我爱你中国", 200, 100, paint);

        // 分割线================================================================
//        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(300, 300, 200, paint);

        // 分割线================================================================

//        Shader shader = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(300, 300, 200, paint);

        // 分割线================================================================

//        Shader shader = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"));
//        paint.setShader(shader);
//        canvas.drawCircle(300, 300, 200, paint);

        // 分割线================================================================

//        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, 200, paint); // 圆形头像的另一种实现方式

        // 分割线================================================================

//        setLayerType(LAYER_TYPE_SOFTWARE,null); // 设置成这样才能有效果
//        // 第一个 Shader：头像的 Bitmap
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
//        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.img_pay);
//        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
//        // ComposeShader：结合两个 Shader
//        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER);
//        paint.setShader(shader);
//        canvas.drawCircle(bitmap2.getWidth()/2, bitmap2.getHeight()/2, bitmap2.getHeight()/2, paint);

        // 分割线================================================================

//        int saved = canvas.saveLayer(null, null);
//
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//        canvas.drawBitmap(bitmap1, 0, 0, paint); // 画方
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)); // 设置 Xfermode
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.img_pay);
//        canvas.drawBitmap(bitmap2, 0, 0, paint); // 画圆
//        paint.setXfermode(null); // 用完及时清除 Xfermode
//
//        canvas.restoreToCount(saved);

        // 分割线================================================================

//        PathEffect pathEffect = new DashPathEffect(new float[]{10, 5}, 10); // 虚线
//        paint.setPathEffect(pathEffect);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(300, 300, 200, paint);

        // 分割线================================================================

//        PathEffect pathEffect = new CornerPathEffect(20); // 拐尖角变圆角
//        paint.setPathEffect(pathEffect);
//        path.moveTo(50,200);
//        path.lineTo(100,400);
//        path.lineTo(400,50);
//        path.lineTo(800,100);
//        paint.setStrokeWidth(10f);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawPath(path, paint);

        // 分割线================================================================

//        PathEffect pathEffect = new DiscretePathEffect(20, 5); // 乱七八糟的线条
//        paint.setPathEffect(pathEffect);
//        path.moveTo(50,200);
//        path.lineTo(100,400);
//        path.lineTo(400,50);
//        path.lineTo(800,100);
//        paint.setStrokeWidth(10f);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawPath(path, paint);

        // 分割线================================================================

        path.moveTo(50,200);
        path.lineTo(100,400);
        path.lineTo(400,50);
        path.lineTo(800,100);
        Path shape = new Path();
        shape.moveTo(5, 0);
        shape.lineTo(0, 10);
        shape.lineTo(10, 10);
        shape.close();
        PathEffect pathEffect = new PathDashPathEffect(shape, 40, 0,
                PathDashPathEffect.Style.TRANSLATE);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);

        // 分割线================================================================

//        path.moveTo(50, 200);
//        path.lineTo(100, 400);
//        path.lineTo(400, 50);
//        path.lineTo(800, 100);
//        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
//        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
//        SumPathEffect pathEffect = new SumPathEffect(dashEffect, discreteEffect); // 两种效果分别绘制一次
//        paint.setStrokeWidth(5f);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setPathEffect(pathEffect);
//        canvas.drawPath(path, paint);

        // 分割线================================================================

//        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
//        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
//        ComposePathEffect pathEffect = new ComposePathEffect(dashEffect, discreteEffect); // 先绘制，再在前一次基础上再绘制
//        path.moveTo(50, 200);
//        path.lineTo(100, 400);
//        path.lineTo(400, 50);
//        path.lineTo(800, 100);
//        paint.setStrokeWidth(5f);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setPathEffect(pathEffect);
//        canvas.drawPath(path, paint);

        // 分割线================================================================

//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5f);
//        paint.setTextSize(80f);
//            paint.setShadowLayer(10, 10, 0, Color.RED); // 绘制阴影
//        setLayerType(LAYER_TYPE_SOFTWARE,null);
//        canvas.drawRoundRect(100,100, 800, 400, 100,100,paint);  // 开启硬件加速下不支持
//        canvas.drawText("文字阴影", 80, 300, paint); // 开启硬件加速下只支持文字阴影

        // 分割线================================================================

//        setLayerType(LAYER_TYPE_SOFTWARE,null);
//        paint.setMaskFilter(new BlurMaskFilter(1, BlurMaskFilter.Blur.SOLID)); // 模糊效果，可以用来做阴影效果
//        canvas.drawBitmap(bitmap1, 100, 100, paint);
//        canvas.drawRoundRect(100,100, 800, 400, 20,20,paint);

        // 分割线================================================================

//        setLayerType(LAYER_TYPE_SOFTWARE,null);
//        // 被废弃
//        paint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));
//        canvas.drawBitmap(bitmap1, 100, 100, paint);

        // 分割线================================================================

//        int count1 = canvas.saveLayer(null,null);
//        // 裁切方法之后的绘制代码，都会被限制在裁切范围内
//        // 意思就是只会显示裁剪区域内的图案
//        canvas.clipRect(100, 100, 400, 400);
//        canvas.drawBitmap(bitmap1, 0, 0, paint);
//        canvas.restoreToCount(count1);
//
//        int count2 = canvas.saveLayer(null,null);
//        path.addCircle(250,600,150, Path.Direction.CCW);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.clipPath(path); // 圆形头像的另一种实现方式
//        canvas.drawBitmap(bitmap1, 0, 0, paint);
//        canvas.restoreToCount(count2);

        // 分割线================================================================

//        canvas.save();
//        canvas.translate(200, 0); // 整张画布右移200
//        canvas.drawBitmap(bitmap, 0, 0, paint); // 所以原点(0,0)也变了
//        canvas.restore();
//
//        canvas.save();
//        canvas.rotate(45, 0, 0);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.scale(1.3f, 1.3f);
//        canvas.drawBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.skew(0, 0.5f);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();

        // 分割线================================================================

//        canvas.save();
//        Matrix matrix = new Matrix();
//        matrix.postScale(1.3f,1.3f);
//        canvas.concat(matrix);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();

        // 分割线================================================================

//        Camera camera = new Camera();
//        canvas.save();
//        camera.rotateX(30); // 旋转 Camera 的三维空间
//        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();

//        camera.save(); // 保存 Camera 的状态
//        camera.rotateX(30); // 旋转 Camera 的三维空间
//        canvas.translate(bitmap.getWidth() /2, bitmap.getHeight() / 2); // 旋转之后把投影移动回来
//        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        canvas.translate(-bitmap.getWidth() /2, -bitmap.getHeight()/2); // 旋转之前把绘制内容移动到轴心（原点）
//        camera.restore(); // 恢复 Camera 的状态
//
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();










    }
}
