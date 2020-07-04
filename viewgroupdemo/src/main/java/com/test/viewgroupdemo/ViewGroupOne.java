package com.test.viewgroupdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
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
public class ViewGroupOne extends ViewGroup {
    String TAG = "ViewGroupOne";
    private View view1;
    private View view2;
    private View view3;

    public ViewGroupOne(Context context) {
        super(context);
        init(context);
    }

    public ViewGroupOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewGroupOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ViewGroupOne(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        view1 = new View(context);
        view1.setBackgroundColor(Color.RED);
        view1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view2 = new View(context);
        view2.setBackgroundColor(Color.GREEN);
        view2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view3 = new View(context);
        view3.setBackgroundColor(Color.BLUE);
        view3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getChildAt(0).layout(100, 100, 600, 600);
        getChildAt(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToTop(v);
            }
        });
        getChildAt(1).layout(400, 400, 700, 800);
        getChildAt(1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToTop(v);
            }
        });
        getChildAt(2).layout(200, 200, getChildAt(2).getMeasuredWidth() + 200, getChildAt(2).getMeasuredHeight() + 200);
        getChildAt(2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToTop(v);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
//        getDefaultSize()
        setMeasuredDimension(resolveSize(900, widthMeasureSpec), resolveSize(900, heightMeasureSpec));
    }

    /**
     * 交换view的位置
     */
    public void moveToTop(View target) {
        // 先确定现在在哪个位置
        int startIndex = indexOfChild(target);
        Log.e(TAG, startIndex + "   startIndex");
        // 计算一共需要几次交换，就可到达最上面
        int count = getChildCount() - 1 - startIndex;
        Log.e(TAG, count + "   count");
        for (int i = 0; i < count; i++) {
            //更新索引
            int fromIndex = indexOfChild(target);
            //目标是它的上层
            int toIndex = fromIndex + 1;
            //获取需要交换位置的两个子View
            View from = target;
            View to = getChildAt(toIndex);

            //先把它们拿出来
            detachViewFromParent(toIndex);
            detachViewFromParent(fromIndex);

            //再放回去，但是放回去的位置(索引)互换了
            attachViewToParent(to, fromIndex, to.getLayoutParams());
            attachViewToParent(from, toIndex, from.getLayoutParams());
        }
        //刷新
        invalidate();
    }

}
