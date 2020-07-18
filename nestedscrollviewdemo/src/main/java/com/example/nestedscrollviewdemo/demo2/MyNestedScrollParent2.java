package com.example.nestedscrollviewdemo.demo2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;

import com.example.nestedscrollviewdemo.R;

import java.util.Arrays;

public class MyNestedScrollParent2 extends LinearLayout implements NestedScrollingParent3 {
    private View top;
    private View bottom;
    private View center;
    private NestedScrollingParentHelper helper = new NestedScrollingParentHelper(this);

    public MyNestedScrollParent2(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public MyNestedScrollParent2(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public MyNestedScrollParent2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        top = findViewById(R.id.demo2_view1);
        center = findViewById(R.id.demo2_view2);
        bottom = findViewById(R.id.demo2_view3);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        Log.e("onNestedScroll 7参数", target + "::" + dxConsumed + "::" + dyConsumed + "::" + dxUnconsumed + "::" + dyUnconsumed + "::" + type+"::"+ Arrays.toString(consumed));
        // 当子控件处理完后交给父控件进行处理
        // dyUnconsumed 子View未消耗的距离
        if (dyUnconsumed <= 0) {
            // 表示子View已经向下滑动到头
            scrollBy(0, dyUnconsumed);
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        Log.e("scrollTo(int x, int y)",x+"::"+y);
        if (y < 0) {
            y = 0;
        }
        if (y > top.getMeasuredHeight()) {
            y = top.getMeasuredHeight();
        }
        super.scrollTo(x, y);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        helper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        helper.onStopNestedScroll(target, type);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.e("onNestedPreScroll 5参数", target + "::" + dx + "::" + dy + "::" + Arrays.toString(consumed) + "::" + type);
        Log.e("onNestedPreScroll 5参数", getScrollY() + "::" + top.getHeight());

        // 向上滑动
        if (dy > 0) {
            if (getScrollY() < top.getHeight()) {
                scrollBy(0, dy);
                consumed[1] = dy;
            } else {
                Log.e("onNestedPreScroll 5参数", "dy > 0");
            }
        }

        // 向下滑动
        if (dy < 0) {
            Log.e("onNestedPreScroll 5参数", String.valueOf(target.canScrollVertically(-1)));
            if (getScrollY() >= 0 && !target.canScrollVertically(-1)) {
                scrollBy(0, dy);
                consumed[1] = dy;
            } else {
                Log.e("onNestedPreScroll 5参数", "dy < 0");
//                scrollBy(0, 0);
            }
        }
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        // 自己判断是否处理
        return false;
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        // 自己处理逻辑
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return helper.getNestedScrollAxes();
    }
}
