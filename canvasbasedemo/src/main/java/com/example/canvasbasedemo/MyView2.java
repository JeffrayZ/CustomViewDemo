package com.example.canvasbasedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

public class MyView2 extends View {
//    private Bitmap loadingBitmap;
    private Drawable loadingBitmap;

    public MyView2(Context context) {
        this(context,null);
    }

    public MyView2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        loadingBitmap = getBitmapFromDrawable(context,R.drawable.mainpage_ic_courseware_downloading);
        loadingBitmap = ContextCompat.getDrawable(context,R.drawable.mainpage_ic_courseware_downloading);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);


//        canvas.drawBitmap(getBitmap(),20,20,null);

        loadingBitmap.setBounds(getWidth()/2 - loadingBitmap.getIntrinsicWidth()/2,0,getWidth()/2 + loadingBitmap.getIntrinsicWidth()/2,loadingBitmap.getIntrinsicHeight());
        loadingBitmap.draw(canvas);
    }

    private Bitmap getBitmapFromDrawable(Context context, @DrawableRes int drawableId) {
        try {
            Drawable drawable = ContextCompat.getDrawable(context, drawableId);
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(Color.GRAY);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    private Bitmap getBitmap() {
//        try {
//            Bitmap bitmap = Bitmap.createBitmap(getWidth() - 40,
//                    getHeight() - 40, Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
////            canvas.drawColor(Color.YELLOW);
//            Path path = new Path();
//            path.addCircle(canvas.getWidth()/2,canvas.getHeight()/2,getWidth()/2, Path.Direction.CW);
//            canvas.clipPath(path);
//            canvas.drawColor(Color.YELLOW);
//            canvas.drawBitmap(loadingBitmap,0,-30,null);
//            return bitmap;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
}
