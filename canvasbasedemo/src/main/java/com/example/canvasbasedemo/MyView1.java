package com.example.canvasbasedemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
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
        this(context,null);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private Paint paint = new Paint();
    private Path path = new Path();
    private Bitmap bitmap;

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

        int saved = canvas.saveLayer(null, null);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        canvas.drawBitmap(bitmap1, 0, 0, paint); // 画方
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)); // 设置 Xfermode
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.img_pay);
        canvas.drawBitmap(bitmap2, 0, 0, paint); // 画圆
        paint.setXfermode(null); // 用完及时清除 Xfermode

        canvas.restoreToCount(saved);















    }
}
