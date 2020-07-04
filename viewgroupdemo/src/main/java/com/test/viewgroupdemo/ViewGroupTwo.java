package com.test.viewgroupdemo;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.test.viewgroupdemo
 * @ClassName: ViewGroupOne
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/7/3 10:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/3 10:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewGroupTwo extends ViewGroup {
    String TAG = "ViewGroupTwo";
    private View view1;

    public ViewGroupTwo(Context context) {
        super(context);
        init(context);
    }

    public ViewGroupTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewGroupTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ViewGroupTwo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getChildAt(0).layout(100, 50, 600, 300);
        getChildAt(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildAt(1).setScaleX(0.5f);
                getChildAt(1).setScaleY(0.5f);
            }
        });
        getChildAt(1).layout(400, 400, 700, 800);
//        getChildAt(1).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "" + v.getLeft() + "::" + v.getTop() + "::" + v.getRight() + "::" + v.getBottom());
//            }
//        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
//        getDefaultSize()
        setMeasuredDimension(resolveSize(900, widthMeasureSpec), resolveSize(900, heightMeasureSpec));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                boolean b = pointInView(getChildAt(1), new float[]{event.getX(), event.getY()});
                if (b) {
                    Log.e(TAG, "" + getChildAt(1).getLeft() + "::" + getChildAt(1).getTop() + "::" + getChildAt(1).getRight() + "::" + getChildAt(1).getBottom());
                    Log.e(TAG, "在view上");
                } else {
                    Log.e(TAG, "不在view上");
                }
                return true;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * @param view   目标view
     * @param points 坐标点(x, y)
     * @return 坐标点是否在view范围内
     */
    private boolean pointInView(View view, float[] points) {
        // 像ViewGroup那样，先对齐一下Left和Top
        points[0] -= view.getLeft();
        points[1] -= view.getTop();
        // 获取View所对应的矩阵
        Matrix matrix = view.getMatrix();
        // 如果矩阵有应用过变换
        if (!matrix.isIdentity()) {
            // 反转矩阵
            matrix.invert(matrix);
            // 映射坐标点
            matrix.mapPoints(points);
        }
        //判断坐标点是否在view范围内
        return points[0] >= 0 && points[1] >= 0 && points[0] < view.getWidth() && points[1] < view.getHeight();
    }

}
